package com.epam.pizzaplanet.model.dao;




import com.epam.pizzaplanet.entity.Status;
import com.epam.pizzaplanet.entity.User;
import com.epam.pizzaplanet.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    int createUser(User user) throws DaoException;
    void createCodeActivation(long userId, String code) throws DaoException;
    long findIdForVerificationCustomer(String code) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    int updateUserStatus(long id, Status status) throws DaoException;

    void updateLogin(long id, String login) throws DaoException;
    void updateFirstName(long id, String firstName) throws DaoException;
    void updateLastName(long id, String lastName) throws DaoException;
    void updateEmail(long id, String email) throws DaoException;
    void updateTelephone(long id, String telephone) throws DaoException;

}
