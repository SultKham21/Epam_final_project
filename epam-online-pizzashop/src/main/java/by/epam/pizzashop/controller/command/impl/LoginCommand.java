package by.epam.pizzashop.controller.command.impl;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.entity.Status;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.UserService;
import by.epam.pizzashop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Login command.
 */
public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult commandResult;
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> user = userService.authenticationUser(login, password);
            if (user.isPresent()) {
                User userAuth = user.get();
                session.setAttribute(SessionAttribute.USER_AUTH, userAuth);
                commandResult = login(userAuth, request);
            } else {
                request.setAttribute(RequestAttribute.LOGIN_ERROR, BundleKey.LOGIN_ERROR);
                return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute while user authenticate ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }

    private CommandResult login(User user, HttpServletRequest request) {
        CommandResult commandResult = null;
        HttpSession session = request.getSession();
        if (user.getStatus().equals(Status.ACTIVE)) {
            switch (user.getRole()) {
                case MANAGER -> {
                    commandResult = new CommandResult(PagePath.MAIN_MANAGER, CommandResult.RoutingType.REDIRECT);
                }
                case CUSTOMER -> {
                    commandResult = new CommandResult(PagePath.MAIN_CUSTOMER, CommandResult.RoutingType.REDIRECT);
                }
                case ADMIN -> {
                    session.setAttribute(SessionAttribute.CURRENT_PAGE, 1);
                    commandResult = new CommandResult(PagePath.MAIN_ADMIN, CommandResult.RoutingType.REDIRECT);
                }
            }
        } else {
            request.setAttribute(RequestAttribute.VERIFICATION_ERROR, BundleKey.VERIFICATION_ERROR);
            return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.FORWARD);
        }
        return commandResult;
    }
}


