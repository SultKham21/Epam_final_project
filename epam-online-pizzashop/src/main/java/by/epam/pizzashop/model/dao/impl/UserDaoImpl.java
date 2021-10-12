package by.epam.pizzashop.model.dao.impl;

import by.epam.pizzashop.entity.Role;
import by.epam.pizzashop.entity.Status;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.DaoException;
import by.epam.pizzashop.model.connection.ConnectionPool;
import by.epam.pizzashop.model.dao.ColumnName;
import by.epam.pizzashop.model.dao.UserDao;
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
 * The type User dao.
 */
public class UserDaoImpl implements UserDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserDaoImpl getInstance() {
        return instance;
    }

    private static final String CREATE_USER = """
            INSERT INTO users (login, password, first_name, last_name, email, telephone, role_id)
            VALUES(?, ?, ?, ?, ?, ?, (SELECT role_id FROM user_role WHERE role=?))
             """;

    private static final String CREATE_CODE = "INSERT INTO code_activation (user_id, code_value) VALUES (?, ?)";

    private static final String FIND_CUSTOMER = "SELECT user_id FROM code_activation WHERE code_value = ?";

    private static final String FIND_USER_BY_LOGIN = """
            SELECT user_id, first_name, last_name, email FROM users WHERE login = ?
            """;

    private static final String FIND_USER_BY_ID = """
                SELECT user_id, first_name, last_name, email FROM users WHERE user_id = ?
            """;

    private static final String AUTHORIZE_USER = """
            SELECT u.user_id, u.first_name, u.last_name, ur.role, us.status FROM users u JOIN user_role ur ON u.role_id=ur.role_id
            JOIN user_status us ON u.status_id=us.status_id WHERE u.login = ? AND u.password = ?
            """;

    private static final String FIND_ALL_managerS = """
            SELECT u.user_id, u.login, u.first_name, u.last_name, u.telephone, u.email, us.status FROM users u 
            JOIN user_status us ON u.status_id=us.status_id
            JOIN user_role ur ON u.role_id=ur.role_id WHERE ur.role='manager'
             """;

    private static final String UPDATE_USER_STATUS = """
            UPDATE users SET status_id = (SELECT status_id FROM user_status WHERE status=?) WHERE user_id=?
            """;

    private static final String FIND_INACTIVE_managerS = """
            SELECT u.user_id, u.first_name, u.last_name, us.status FROM users u JOIN user_status us ON u.status_id=us.status_id
            JOIN user_role ur ON u.role_id=ur.role_id WHERE ur.role='manager' AND us.status='INACTIVE'
             """;

    private static final String UPDATE_USER_LOGIN = "UPDATE users SET login =? WHERE user_id =?";
    private static final String UPDATE_USER_FIRST_NAME = "UPDATE users SET first_name =? WHERE user_id =?";
    private static final String UPDATE_USER_LAST_NAME = "UPDATE users SET last_name =? WHERE user_id =?";
    private static final String UPDATE_USER_EMAIL = "UPDATE users SET email =? WHERE user_id =?";
    private static final String UPDATE_USER_TELEPHONE = "UPDATE users SET telephone =? WHERE user_id =?";


    @Override
    public int createUser(User user) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelephone());
            preparedStatement.setString(7, user.getRole().name());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createUser() ", e);
            throw new DaoException("SQLException in method createUser() ", e);
        }
        return result;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User.Builder()
                            .setUserId(resultSet.getLong(USER_ID))
                            .setFirstName(resultSet.getString(USER_FIRST_NAME))
                            .setLastName(resultSet.getString(USER_LAST_NAME))
                            .setEmail(resultSet.getString(USER_EMAIL))
                            .build();
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findByLogin() ", e);
            throw new DaoException("SQLException in method findByLogin() ", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User.Builder()
                            .setUserId(resultSet.getLong(USER_ID))
                            .setFirstName(resultSet.getString(USER_FIRST_NAME))
                            .setLastName(resultSet.getString(USER_LAST_NAME))
                            .setEmail(resultSet.getString(USER_EMAIL))
                            .build();
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findById() ", e);
            throw new DaoException("SQLException in method findById() ", e);
        }
        return Optional.empty();
    }

    @Override
    public void createCodeActivation(long userId, String code) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CODE)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createUser() ", e);
            throw new DaoException("SQLException in method createUser() ", e);
        }
    }

    @Override
    public long findIdForVerificationCustomer(String code) throws DaoException {
        long id = 0L;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER)) {
            preparedStatement.setString(1, code);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    id = rs.getLong(USER_ID);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findIdForActivationCustomer() ", e);
            throw new DaoException("SQLException in method findIdForActivationCustomer() ", e);
        }
        return id;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AUTHORIZE_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new User.Builder()
                            .setUserId(resultSet.getLong(USER_ID))
                            .setFirstName(resultSet.getString(USER_FIRST_NAME))
                            .setLastName(resultSet.getString(USER_LAST_NAME))
                            .setRole(Role.valueOf(resultSet.getString(ColumnName.USER_ROLE)))
                            .setStatus(Status.valueOf(resultSet.getString(USER_STATUS)))
                            .build());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method authenticationUser() ", e);
            throw new DaoException("SQLException in method authenticationUser() ", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllmanagers() throws DaoException {
        List<User> managers = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_managerS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User.Builder()
                            .setUserId(resultSet.getLong(USER_ID))
                            .setLogin(resultSet.getString(USER_LOGIN))
                            .setFirstName(resultSet.getString(USER_FIRST_NAME))
                            .setLastName(resultSet.getString(USER_LAST_NAME))
                            .setTelephone(resultSet.getString(USER_TELEPHONE))
                            .setEmail(resultSet.getString(USER_EMAIL))
                            .setStatus(Status.valueOf(resultSet.getString(USER_STATUS)))
                            .build();
                    managers.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllmanagers() ", e);
            throw new DaoException("SQLException in method findAllmanagers() ", e);
        }
        return managers;
    }

    @Override
    public int updateUserStatus(long id, Status status) throws DaoException {
        int result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_STATUS)) {
            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateUserStatus() ", e);
            throw new DaoException("SQLException in method updateUserStatus() ", e);
        }
        return result;
    }

    @Override
    public List<User> findInactivemanagers() throws DaoException {
        List<User> inactivemanagers = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_INACTIVE_managerS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User.Builder()
                            .setUserId(resultSet.getLong(USER_ID))
                            .setFirstName(resultSet.getString(USER_FIRST_NAME))
                            .setLastName(resultSet.getString(USER_LAST_NAME))
                            .setStatus(Status.valueOf(resultSet.getString(USER_STATUS)))
                            .build();
                    inactivemanagers.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findInactivemanagers() ", e);
            throw new DaoException("SQLException in method findInactivemanagers() ", e);
        }
        return inactivemanagers;
    }

    @Override
    public void updateLogin(long id, String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateLogin() ", e);
            throw new DaoException("SQLException in method updateLogin() ", e);
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
            logger.log(Level.ERROR, "SQLException in method updateFirstName() ", e);
            throw new DaoException("SQLException in method updateFirstName() ", e);
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
            logger.log(Level.ERROR, "SQLException in method updateLastName() ", e);
            throw new DaoException("SQLException in method updateLastName() ", e);
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
            logger.log(Level.ERROR, "SQLException in method updateEmail() ", e);
            throw new DaoException("SQLException in method updateEmail() ", e);
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
            logger.log(Level.ERROR, "SQLException in method updateTelephone() ", e);
            throw new DaoException("SQLException in method updateTelephone() ", e);
        }
    }
}
