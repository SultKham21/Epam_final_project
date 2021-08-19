package com.epam.khamroev.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 4;
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;


    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionFactory.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Couldn't create connection to database: " + e.getMessage());
            }
        }
        if (freeConnections.isEmpty()) {
            logger.log(Level.FATAL, "Connection poll wasn't created, pool size: " + freeConnections.size());
            throw new RuntimeException("Connection poll wasn't created, pool size: " + freeConnections.size());
        }
        logger.log(Level.INFO, "Connection pool was created");
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException in method getConnection() " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenAwayConnections.remove(connection)) {
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "InterruptedException in method releaseConnection() " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "InterruptedException in method destroyPool() " + e.getMessage());
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);

            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQLException in method deregisterDrivers() " + e.getMessage());
            }
        });
    }

}
