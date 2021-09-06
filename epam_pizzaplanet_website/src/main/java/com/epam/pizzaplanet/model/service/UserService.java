package com.epam.pizzaplanet.model.service;

import com.epam.pizzaplanet.exception.ServiceException;
import com.epam.pizzaplanet.entity.Status;
import com.epam.pizzaplanet.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> createUser(String login, String password, String firstName, String lastName, String email, String telephone, String role) throws ServiceException;

    Optional<User> authenticationUser(String login, String password) throws ServiceException;
     Map<String,String> isFormValid(String login, String password, String firstName, String lastName, String email, String telephone);

    boolean updateCustomerStatus(String code) throws ServiceException;



    boolean updateLogin(long id, String login) throws ServiceException;

    void updateFirstName(long id, String firstName) throws ServiceException;

    void updateLastName(long id, String lastName) throws ServiceException;

    void updateEmail(long id, String email) throws ServiceException;

    void updateTelephone(long id, String telephone) throws ServiceException;
}