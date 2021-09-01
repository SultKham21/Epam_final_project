package com.epam.khamroev.dao.impl;

import com.epam.khamroev.entity.Role;
import com.epam.khamroev.entity.Status;
import com.epam.khamroev.entity.User;
import com.epam.khamroev.exception.DaoException;
import com.epam.khamroev.pool.ConnectionPool;
import com.epam.khamroev.dao.ColumnName;
import com.epam.khamroev.dao.UserDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CREATE_USER = """
            INSERT INTO users (login, password, name, surname, e_mail, phoneNumber,roleId,statusId)
            VALUES(?, ?, ?, ?, ?, ?, (SELECT roleId FROM roles WHERE roleId=?),
            (SELECT statusId FROM statuses WHERE statusId=?))
             """;

    private static final String FIND_BY_LOGIN = "SELECT login FROM users WHERE login=?";

    private static final String IDENT_USER = """
            SELECT u.first_name, u.last_name, ur.role FROM users u JOIN user_role ur ON u.role_id=ur.role_id
            WHERE u.login=? AND u.password=?
            """;

    private static final String UPDATE_USER_LOGIN = "UPDATE users SET login =? WHERE user_id =?";
    private static final String UPDATE_USER_FIRST_NAME = "UPDATE users SET first_name =? WHERE user_id =?";
    private static final String UPDATE_USER_LAST_NAME = "UPDATE users SET last_name =? WHERE user_id =?";
    private static final String UPDATE_USER_EMAIL = "UPDATE users SET email =? WHERE user_id =?";
    private static final String UPDATE_USER_TELEPHONE = "UPDATE users SET telephone =? WHERE user_id =?";


    @Override
    public void createUser(User user) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_USER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getTelephone());
            ps.setString(7, user.getRole().name());
            ps.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createUser() " ,e);
            throw new DaoException("SQLException in method createUser() " ,e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setLogin(login);

                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createUser() " , e);
            throw new DaoException("SQLException in method createUser() " ,e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> authenticationUser(User user) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IDENT_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setFirstName(rs.getString(ColumnName.USER_FIRST_NAME));
                    user.setLastName(rs.getString((ColumnName.USER_LAST_NAME)));
                    user.setRole(Role.valueOf(rs.getString(ColumnName.USER_ROLE_ID)));
                    return Optional.of(user);

                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method authenticationUser() " + e.getMessage());
            throw new DaoException("SQLException in method authenticationUser() " + e.getMessage());
        }
        return Optional.empty();
    }



    @Override
    public void updateLogin(long id, String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateLogin() " ,e);
            throw new DaoException("SQLException in method updateLogin() " , e);
        }
    }

    @Override
    public void updateFirstName(long id, String firstName) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_FIRST_NAME)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateFirstName() " ,e);
            throw new DaoException("SQLException in method updateFirstName() " ,e);
        }
    }

    @Override
    public void updateLastName(long id, String lastName) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LAST_NAME)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateLastName() " ,e);
            throw new DaoException("SQLException in method updateLastName() " , e);
        }
    }

    @Override
    public void updateEmail(long id, String email) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateEmail() " ,e);
            throw new DaoException("SQLException in method updateEmail() " , e);
        }
    }

    @Override
    public void updateTelephone(long id, String telephone) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_TELEPHONE)) {
            preparedStatement.setString(1, telephone);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateTelephone() " , e);
            throw new DaoException("SQLException in method updateTelephone() " ,e);
        }
    }
}
