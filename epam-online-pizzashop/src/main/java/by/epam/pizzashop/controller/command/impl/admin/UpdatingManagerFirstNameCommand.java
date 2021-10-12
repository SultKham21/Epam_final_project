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
 * The type Updating manager first name command.
 */
public class UpdatingManagerFirstNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(SessionAttribute.manager_ID);
        String newFirstName = request.getParameter(RequestParameter.UPDATING_manager_FIRST_NAME);
        UserService userService = UserServiceImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> managers;

        if (!userValidator.isValidStringParameter(newFirstName)) {
            request.setAttribute(RequestAttribute.UPDATING_manager_FIRST_NAME_ERROR, BundleKey.FIRST_NAME_ERROR);
            return new CommandResult(PagePath.UPDATING_MANAGER_FIRST_NAME, CommandResult.RoutingType.FORWARD);
        }
        try {
            userService.updateFirstName(id, newFirstName);
            managers = userService.findAllmanagers();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while update first name or find all managers ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_MANAGERS, managers);
        return new CommandResult(PagePath.ALL_MANAGERS, CommandResult.RoutingType.REDIRECT);
    }
}
