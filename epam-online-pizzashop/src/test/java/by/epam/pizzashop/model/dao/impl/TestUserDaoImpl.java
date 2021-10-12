package by.epam.pizzashop.model.dao.impl;

import by.epam.pizzashop.entity.Role;
import by.epam.pizzashop.entity.Status;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.DaoException;
import by.epam.pizzashop.model.connection.ConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestUserDaoImpl {

    @InjectMocks
    private UserDaoImpl userDao;
    @Mock
    private ConnectionPool mockConnectionPool;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private User user;
    private User incorrectUser;

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockConnectionPool.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        when(mockPreparedStatement.executeUpdate()).thenReturn(0, 1);
        user = new User.Builder()
                .setUserId(1)
                .setLogin("login")
                .setPassword("password")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setTelephone("+375291112233")
                .setEmail("email@mail.ru")
                .setRole(Role.MANAGER)
                .setStatus(Status.ACTIVE)
                .build();
        incorrectUser = new User.Builder()
                .setLogin(null)
                .setPassword("password")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setTelephone("+375291112233")
                .setEmail("email@mail.ru")
                .setRole(Role.MANAGER)
                .setStatus(Status.ACTIVE)
                .build();
    }

    @Test
    public void createUserTrueTest() throws DaoException {
        int result = userDao.createUser(user);
        assertEquals(1, result);
    }

    @Test
    public void createUserFalseTest() throws DaoException {
        int result = userDao.createUser(user);
        assertNotEquals(2, result);
    }

    @Test
    public void createUserExceptionTest() {
        Assertions.assertThrows(DaoException.class, () -> userDao.createUser(incorrectUser));
    }

    @Test
    public void findByLoginTrueTest() throws DaoException {
        Optional<User> actualUser = userDao.findByLogin("login");
        assertEquals("firstName", actualUser.get().getFirstName());
    }

    @Test
    public void findByLoginFalseTest() throws DaoException {
        Optional<User> actualUser = userDao.findByLogin("login");
        assertNotEquals("firstName", actualUser.get().getLastName());
    }

    @Test
    public void findByIdTrueTest() throws DaoException {
        Optional<User> actualUser = userDao.findById(1);
        assertEquals("firstName", actualUser.get().getFirstName());
    }


}
