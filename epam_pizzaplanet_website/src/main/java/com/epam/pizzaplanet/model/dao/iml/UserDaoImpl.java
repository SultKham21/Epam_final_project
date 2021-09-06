package com.epam.pizzaplanet.model.dao.iml;



import com.epam.pizzaplanet.entity.Role;
import com.epam.pizzaplanet.entity.Status;
import com.epam.pizzaplanet.entity.User;
import com.epam.pizzaplanet.exception.DaoException;
import com.epam.pizzaplanet.model.dao.ColumnName;
import com.epam.pizzaplanet.model.dao.UserDao;
import com.epam.pizzaplanet.model.pool.ConnectionPool;
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
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl(){
    }
    public static UserDaoImpl getInstance(){
        return instance;
    }

    private static final String CREATE_USER = """
            INSERT INTO users (login, password, first_name, last_name, email, telephone, role_id)
            VALUES(?, ?, ?, ?, ?, ?, (SELECT role_id FROM user_role WHERE role=?))
             """;

    private static final String CREATE_CODE = "INSERT INTO code_activation (user_id, code_value) VALUES (?, ?)";

    private static final String FIND_CUSTOMER = "SELECT user_id FROM code_activation WHERE code_value = ?";

    private static final String FIND_BY_LOGIN = "SELECT user_id, first_name, last_name, email FROM users WHERE login=?";

    private static final String AUTHORIZE_USER = """
            SELECT u.first_name, u.last_name, ur.role FROM users u JOIN user_role ur ON u.role_id=ur.role_id
            WHERE u.login=? AND u.password=?
            """;



    private static final String UPDATE_USER_STATUS = """
            UPDATE users SET status_id = (SELECT status_id FROM user_status WHERE status=?) WHERE user_id=?
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
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getLong(ColumnName.USER_ID));
                    user.setFirstName(resultSet.getString(ColumnName.USER_FIRST_NAME));
                    user.setLastName(resultSet.getString(ColumnName.USER_LAST_NAME));
                    user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createUser() ", e);
            throw new DaoException("SQLException in method createUser() ", e);
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
                    id = rs.getLong(ColumnName.USER_ID);
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
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    User user =new User();
                    user.setFirstName(rs.getString(ColumnName.USER_FIRST_NAME));
                    user.setLastName(rs.getString(ColumnName.USER_LAST_NAME));
                    user.setRole(Role.valueOf(rs.getString(ColumnName.USER_ROLE)));

                    return Optional.of(user);


                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method authenticationUser() ", e);
            throw new DaoException("SQLException in method authenticationUser() ", e);
        }
        return Optional.empty();
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
