package by.epam.pizzashop.model.service.impl;

import by.epam.pizzashop.entity.Role;
import by.epam.pizzashop.entity.Status;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.DaoException;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.dao.UserDao;
import by.epam.pizzashop.model.dao.impl.UserDaoImpl;
import by.epam.pizzashop.model.service.UserService;
import by.epam.pizzashop.model.util.PasswordEncoder;
import by.epam.pizzashop.model.validation.UserValidator;
import by.epam.pizzashop.model.validation.impl.UserValidatorImpl;
import by.epam.pizzashop.model.verification.EmailSending;
import by.epam.pizzashop.model.verification.impl.EmailSendingImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static by.epam.pizzashop.controller.command.RequestParameter.*;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {
    private Logger logger = LogManager.getLogger();
    private static final String ROLE_CUSTOMER_IN_RUSSIAN = "КЛИЕНТ";
    private static final String HEADER_FOR_VERIFICATION_CUSTOMER = "Activation from Alpha Pharmacy";
    private static final String MESSAGE_FOR_VERIFICATION_CUSTOMER = """
            Hello, %s  %s welcome to Alpha Pharmacy. Your activation code is %s
            """;
    private UserDao userDao = UserDaoImpl.getInstance();
    private UserValidator userValidator = UserValidatorImpl.getInstance();
    private EmailSending emailSending = EmailSendingImpl.getInstance();

    private UserServiceImpl() {

    }

    private static UserServiceImpl instance = new UserServiceImpl();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> createUser(String login, String password,
                                     String firstName, String lastName, String email, String telephone,
                                     String role) throws ServiceException {
        int result;
        User user = new User();
        if (role.equals(Role.CUSTOMER.toString()) || role.equals(Role.MANAGER.toString())) {
            user.setRole(Role.valueOf(role));
        }
        String encodedPassword = PasswordEncoder.createPasswordEncoded(password);

        user.setLogin(login);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setTelephone(telephone);

        try {
            if (userDao.findByLogin(user.getLogin()).isPresent()) {
                return Optional.empty();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Such login already exists ", e);
            throw new ServiceException("Such login already exists ", e);
        }
        try {
            result = userDao.createUser(user);
        } catch (DaoException ex) {
            logger.log(Level.ERROR, "DaoException is in method createUser(), when create user ", ex);
            throw new ServiceException("DaoException is in method createUser(), when create user ", ex);
        }
        if (user.getRole().
                equals(Role.CUSTOMER) && result > 0) {
            try {
                Optional<User> userReg = userDao.findByLogin(user.getLogin());
                if (userReg.isPresent()) {
                    String code = UUID.randomUUID().toString();
                    userDao.createCodeActivation(userReg.get().getUserId(), code);
                    emailSending.sendEmail(userReg.get(), code, HEADER_FOR_VERIFICATION_CUSTOMER,
                            MESSAGE_FOR_VERIFICATION_CUSTOMER);
                }
            } catch (DaoException e) {
                logger.log(Level.ERROR, "DaoException is in method createUser(), when send code", e);
                throw new ServiceException("DaoException is in method createUser(), when send code ", e);
            }
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> authenticationUser(String login, String password) throws ServiceException {
        Optional<User> userFromDb;
        String passwordEncoded = PasswordEncoder.createPasswordEncoded(password);
        try {
            userFromDb = userDao.findUserByLoginAndPassword(login, passwordEncoded);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method authenticationUser() ", e);
            throw new ServiceException("DaoException is in method authenticationUser() ", e);
        }
        if (userFromDb.isEmpty()) {
            userFromDb = Optional.empty();
        }
        return userFromDb;
    }

    @Override
    public Map<String, String> isFormValid(String login, String password, String firstName,
                                           String lastName, String email, String telephone) {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put(LOGIN, login);
        userParameters.put(PASSWORD, password);
        userParameters.put(FIRST_NAME, firstName);
        userParameters.put(LAST_NAME, lastName);
        userParameters.put(EMAIL, email);
        userParameters.put(TELEPHONE, telephone);
        userValidator.isValidForm(userParameters);
        return userParameters;
    }

    @Override
    public List<User> findAllmanagers() throws ServiceException {
        List<User> managers;
        try {
            managers = userDao.findAllmanagers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllmanagers() ", e);
            throw new ServiceException("DaoException is in method findAllmanagers() ", e);
        }
        return managers;
    }

    @Override
    public void updatemanagerStatus(String id, Status status) throws ServiceException {
        try {
            userDao.updateUserStatus(Long.parseLong(id), status);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method  updatemanagerStatus() ", e);
            throw new ServiceException("DaoException is in method updatemanagerStatus() ", e);
        }
    }

    @Override
    public boolean updateCustomerStatus(String code) throws ServiceException {
        try {
            long id = userDao.findIdForVerificationCustomer(code);
            if (id != 0) {
                userDao.updateUserStatus(id, Status.ACTIVE);
                return true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method changeCustomerStatus() ", e);
            throw new ServiceException("DaoException is in method changeCustomerStatus ", e);
        }
        return false;
    }

    @Override
    public List<User> findInactivemanagers() throws ServiceException {
        List<User> inactivemanagers;
        try {
            inactivemanagers = userDao.findInactivemanagers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findInactivemanagers() ", e);
            throw new ServiceException("DaoException is in method findInactivemanagers() ", e);
        }
        return inactivemanagers;
    }

    @Override
    public boolean updateLogin(long id, String login) throws ServiceException {
        try {
            Optional<User> user = userDao.findByLogin(login);
            if (user.isEmpty()) {
                userDao.updateLogin(id, login);
                return true;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateLogin() ", e);
            throw new ServiceException("DaoException is in method updateLogin() ", e);
        }
        return false;
    }

    @Override
    public void updateFirstName(long id, String firstName) throws ServiceException {
        try {
            userDao.updateFirstName(id, firstName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateFirstName() ", e);
            throw new ServiceException("DaoException is in method updateFirstName() ", e);
        }
    }

    @Override
    public void updateLastName(long id, String lastName) throws ServiceException {
        try {
            userDao.updateLastName(id, lastName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateLastName() ", e);
            throw new ServiceException("DaoException is in method updateLastName() ", e);
        }
    }

    @Override
    public void updateEmail(long id, String email) throws ServiceException {
        try {
            userDao.updateEmail(id, email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateEmail() ", e);
            throw new ServiceException("DaoException is in method updateEmail() ", e);
        }
    }

    @Override
    public void updateTelephone(long id, String telephone) throws ServiceException {
        try {
            userDao.updateTelephone(id, telephone);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateTelephone() ", e);
            throw new ServiceException("DaoException is in method updateTelephone() ", e);
        }
    }

    //TODO
    private Role convertRole(String role) {
        if (role.equals(ROLE_CUSTOMER_IN_RUSSIAN)) {
            return Role.CUSTOMER;
        } else {
            return Role.MANAGER;
        }
    }
}
