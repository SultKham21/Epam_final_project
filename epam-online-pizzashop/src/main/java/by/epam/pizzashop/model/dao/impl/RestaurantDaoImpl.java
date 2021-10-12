package by.epam.pizzashop.model.dao.impl;


import by.epam.pizzashop.entity.Restaurant;
import by.epam.pizzashop.exception.DaoException;
import by.epam.pizzashop.model.connection.ConnectionPool;

import by.epam.pizzashop.model.dao.RestaurantDao;
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

import static by.epam.pizzashop.model.dao.ColumnName.*;

/**
 * The type Restaurant dao.
 */
public class RestaurantDaoImpl implements RestaurantDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static RestaurantDaoImpl instance = new RestaurantDaoImpl();

    private RestaurantDaoImpl() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RestaurantDaoImpl getInstance() {
        return instance;
    }

    private static final String FIND_RESTAURANTS = """
            SELECT restaurant_id, number, city, street, house, block FROM restaurants LIMIT ?, 15
            """;

    private static final String FIND_RESTAURANTS_BY_CITY = """
            SELECT restaurant_id, number, city, street, house, block FROM restaurants WHERE city=?
            """;

    private static final String CREATE_RESTAURANT = """
            INSERT INTO restaurants (number, city, street, house, block)
            VALUES(?, ?, ?, ?, ?)
             """;

    private static final String COUNT_RESTAURANTS = "SELECT COUNT(*) FROM restaurants";

    private static final String FIND_RESTAURANT_BY_ID = """
            SELECT restaurant_id, number, city, street, house, block FROM restaurants WHERE restaurant_id = ?
            """;

    private static final String UPDATE_RESTAURANT_NUMBER = "UPDATE restaurants SET number =? WHERE restaurant_id =?";
    private static final String UPDATE_RESTAURANT_CITY = "UPDATE restaurants SET city =? WHERE restaurant_id =?";
    private static final String UPDATE_RESTAURANT_STREET = "UPDATE restaurants SET street =? WHERE restaurant_id =?";
    private static final String UPDATE_RESTAURANT_HOUSE = "UPDATE restaurants SET house =? WHERE restaurant_id =?";
    private static final String UPDATE_RESTAURANT_BLOCK = "UPDATE restaurants SET block =? WHERE restaurant_id =?";


    @Override
    public List<Restaurant> findRestaurants(int startingPharmacy) throws DaoException {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESTAURANTS)) {
            preparedStatement.setInt(1, startingPharmacy);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Restaurant restaurant = new Restaurant.Builder()
                            .setRestaurantId(resultSet.getLong(RESTAURANT_ID))
                            .setNumber(resultSet.getInt(RESTAURANT_NUMBER))
                            .setCity(resultSet.getString(RESTAURANT_CITY))
                            .setStreet(resultSet.getString(RESTAURANT_STREET))
                            .setHouse(resultSet.getString(RESTAURANT_HOUSE))
                            .setBlock(resultSet.getInt(RESTAURANT_BLOCK))
                            .build();
                    restaurants.add(restaurant);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllPharmacies() ", e);
            throw new DaoException("SQLException in method findAllPharmacies() ", e);
        }
        return restaurants;
    }

    @Override
    public List<Restaurant> findRestaurantsByCity(String city) throws DaoException {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESTAURANTS_BY_CITY)) {
            preparedStatement.setString(1, city);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Restaurant restaurant = new Restaurant.Builder()
                            .setRestaurantId(resultSet.getLong(RESTAURANT_ID))
                            .setNumber(resultSet.getInt(RESTAURANT_NUMBER))
                            .setCity(resultSet.getString(RESTAURANT_CITY))
                            .setStreet(resultSet.getString(RESTAURANT_STREET))
                            .setHouse(resultSet.getString(RESTAURANT_HOUSE))
                            .setBlock(resultSet.getInt(RESTAURANT_BLOCK))
                            .build();
                    restaurants.add(restaurant);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllPharmaciesByCity() ", e);
            throw new DaoException("SQLException in method findAllPharmaciesByCity() ", e);
        }
        return restaurants;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RESTAURANT)) {
            preparedStatement.setInt(1, restaurant.getNumber());
            preparedStatement.setString(2, restaurant.getCity());
            preparedStatement.setString(3, restaurant.getStreet());
            preparedStatement.setString(4, restaurant.getHouse());
            preparedStatement.setInt(5, restaurant.getBlock());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createRestaurant() ", e);
            throw new DaoException("SQLException in method createRestaurant() ", e);
        }
    }

    @Override
    public int findRestaurantsNumber() throws DaoException {
        int pharmaciesNumber = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_RESTAURANTS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    pharmaciesNumber = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findPharmaciesNumber() ", e);
            throw new DaoException("SQLException in method findPharmaciesNumber() ", e);
        }
        return pharmaciesNumber;
    }

    @Override
    public Optional<Restaurant> findRestaurantById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESTAURANT_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Restaurant restaurant = new Restaurant.Builder()
                            .setRestaurantId(resultSet.getLong(RESTAURANT_ID))
                            .setNumber(resultSet.getInt(RESTAURANT_NUMBER))
                            .setCity(resultSet.getString(RESTAURANT_CITY))
                            .setStreet(resultSet.getString(RESTAURANT_STREET))
                            .setHouse(resultSet.getString(RESTAURANT_HOUSE))
                            .setBlock(resultSet.getInt(RESTAURANT_BLOCK))
                            .build();
                    return Optional.of(restaurant);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findPharmacyById() ", e);
            throw new DaoException("SQLException in method findPharmacyById() ", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateNumber(long id, int number) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_NUMBER)) {
            preparedStatement.setInt(1, number);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateNumber() ", e);
            throw new DaoException("SQLException in method updateNumber() ", e);
        }
    }

    @Override
    public void updateCity(long id, String city) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_CITY)) {
            preparedStatement.setString(1, city);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateCity() ", e);
            throw new DaoException("SQLException in method updateCity() ", e);
        }
    }

    @Override
    public void updateStreet(long id, String street) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_STREET)) {
            preparedStatement.setString(1, street);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateStreet() ", e);
            throw new DaoException("SQLException in method updateStreet() ", e);
        }
    }

    @Override
    public void updateHouse(long id, String house) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_HOUSE)) {
            preparedStatement.setString(1, house);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateHouse() ", e);
            throw new DaoException("SQLException in method updateHouse() ", e);
        }
    }

    @Override
    public void updateBlock(long id, int block) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_BLOCK)) {
            preparedStatement.setInt(1, block);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateBlock() ", e);
            throw new DaoException("SQLException in method updateBlock() ", e);
        }
    }
}
