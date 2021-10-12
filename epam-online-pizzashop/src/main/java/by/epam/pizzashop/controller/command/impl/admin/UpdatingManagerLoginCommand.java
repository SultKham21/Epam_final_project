package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.UserService;
import by.epam.pizzashop.model.service.impl.UserServiceImpl;
import by.epam.pizzashop.model.validation.UserValidator;
import by.epam.pizzashop.model.validation.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Updating manager login command.
 */
public class UpdatingManagerLoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(SessionAttribute.manager_ID);
        String newLogin = request.getParameter(RequestParameter.UPDATING_manager_LOGIN);
        UserService userService = UserServiceImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> managers;

        if(!userValidator.isValidStringParameter(newLogin)) {
            request.setAttribute(RequestAttribute.UPDATING_manager_LOGIN_ERROR, BundleKey.USER_LOGIN_ERROR);
            return new CommandResult(PagePath.UPDATING_MANAGER_LOGIN, CommandResult.RoutingType.FORWARD);
        }
        try {
            if (userService.updateLogin(id, newLogin)) {
                managers = userService.findAllmanagers();
                session.setAttribute(SessionAttribute.ALL_MANAGERS, managers);
                return new CommandResult(PagePath.ALL_MANAGERS, CommandResult.RoutingType.REDIRECT);
            } else {
                request.setAttribute(RequestAttribute.REGISTRATION_ERROR, BundleKey.REGISTRATION_ERROR);
                return new CommandResult(PagePath.UPDATING_MANAGER_LOGIN, CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute find all managers ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}
