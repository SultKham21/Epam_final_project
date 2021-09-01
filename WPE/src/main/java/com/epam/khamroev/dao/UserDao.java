package com.epam.khamroev.dao;

import com.epam.khamroev.entity.Restaurant;
import com.epam.khamroev.entity.Status;
import com.epam.khamroev.entity.User;
import com.epam.khamroev.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void createUser(User user) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
    Optional<User> authenticationUser(User user) throws DaoException;
    void updateLogin(long id, String login) throws DaoException;
    void updateFirstName(long id, String firstName) throws DaoException;
    void updateLastName(long id, String lastName) throws DaoException;
    void updateEmail(long id, String email) throws DaoException;
    void updateTelephone(long id, String telephone) throws DaoException;
}
