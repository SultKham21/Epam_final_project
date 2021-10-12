package by.epam.pizzashop.model.service;

import by.epam.pizzashop.entity.Status;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Create user optional.
     *
     * @param login     the login
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param telephone the telephone
     * @param role      the role
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> createUser(String login, String password, String firstName, String lastName, String email, String telephone, String role) throws ServiceException;

    /**
     * Authentication user optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> authenticationUser(String login, String password) throws ServiceException;

    /**
     * Is form valid map.
     *
     * @param login     the login
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param telephone the telephone
     * @return the map
     */
    Map<String,String> isFormValid(String login, String password, String firstName, String lastName, String email, String telephone);

    /**
     * Find allmanagers list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllmanagers() throws ServiceException;

    /**
     * Update customer status boolean.
     *
     * @param code the code
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateCustomerStatus(String code) throws ServiceException;

    /**
     * Updatemanager status.
     *
     * @param id     the id
     * @param status the status
     * @throws ServiceException the service exception
     */
    void updatemanagerStatus(String id, Status status) throws ServiceException;

    /**
     * Find inactivemanagers list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findInactivemanagers() throws ServiceException;

    /**
     * Update login boolean.
     *
     * @param id    the id
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateLogin(long id, String login) throws ServiceException;

    /**
     * Update first name.
     *
     * @param id        the id
     * @param firstName the first name
     * @throws ServiceException the service exception
     */
    void updateFirstName(long id, String firstName) throws ServiceException;

    /**
     * Update last name.
     *
     * @param id       the id
     * @param lastName the last name
     * @throws ServiceException the service exception
     */
    void updateLastName(long id, String lastName) throws ServiceException;

    /**
     * Update email.
     *
     * @param id    the id
     * @param email the email
     * @throws ServiceException the service exception
     */
    void updateEmail(long id, String email) throws ServiceException;

    /**
     * Update telephone.
     *
     * @param id        the id
     * @param telephone the telephone
     * @throws ServiceException the service exception
     */
    void updateTelephone(long id, String telephone) throws ServiceException;
}
