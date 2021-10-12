package by.epam.pizzashop.model.dao;

import by.epam.pizzashop.entity.Status;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Create user int.
     *
     * @param user the user
     * @return the int
     * @throws DaoException the dao exception
     */
    int createUser(User user) throws DaoException;

    /**
     * Create code activation.
     *
     * @param userId the user id
     * @param code   the code
     * @throws DaoException the dao exception
     */
    void createCodeActivation(long userId, String code) throws DaoException;

    /**
     * Find id for verification customer long.
     *
     * @param code the code
     * @return the long
     * @throws DaoException the dao exception
     */
    long findIdForVerificationCustomer(String code) throws DaoException;

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findById(long id) throws DaoException;

    /**
     * Find user by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Find allmanagers list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllmanagers() throws DaoException;

    /**
     * Update user status int.
     *
     * @param id     the id
     * @param status the status
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateUserStatus(long id, Status status) throws DaoException;

    /**
     * Find inactivemanagers list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findInactivemanagers() throws DaoException;

    /**
     * Update login.
     *
     * @param id    the id
     * @param login the login
     * @throws DaoException the dao exception
     */
    void updateLogin(long id, String login) throws DaoException;

    /**
     * Update first name.
     *
     * @param id        the id
     * @param firstName the first name
     * @throws DaoException the dao exception
     */
    void updateFirstName(long id, String firstName) throws DaoException;

    /**
     * Update last name.
     *
     * @param id       the id
     * @param lastName the last name
     * @throws DaoException the dao exception
     */
    void updateLastName(long id, String lastName) throws DaoException;

    /**
     * Update email.
     *
     * @param id    the id
     * @param email the email
     * @throws DaoException the dao exception
     */
    void updateEmail(long id, String email) throws DaoException;

    /**
     * Update telephone.
     *
     * @param id        the id
     * @param telephone the telephone
     * @throws DaoException the dao exception
     */
    void updateTelephone(long id, String telephone) throws DaoException;
}
