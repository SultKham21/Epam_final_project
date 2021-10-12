package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.Command;
import by.epam.pizzashop.controller.command.CommandResult;
import by.epam.pizzashop.controller.command.PagePath;
import by.epam.pizzashop.controller.command.SessionAttribute;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.UserService;
import by.epam.pizzashop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type All managers command.
 */
public class AllManagersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        List<User> managers;
        try {
            managers = userService.findAllmanagers();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find all managers ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.ALL_MANAGERS, managers);
        return new CommandResult(PagePath.ALL_MANAGERS, CommandResult.RoutingType.REDIRECT);
    }
}