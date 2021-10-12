package by.epam.pizzashop.controller.command.impl;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.UserService;
import by.epam.pizzashop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Verification customer command.
 */
public class VerificationCustomerCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        UserService userService = UserServiceImpl.getInstance();
        String code = request.getParameter(RequestParameter.CODE);
        try {
            if (userService.updateCustomerStatus(code)) {
              return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
            } else {
                request.setAttribute(RequestAttribute.CODE_VERIFICATION_ERROR, BundleKey.CODE_VERIFICATION_ERROR);
                return new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute while update customer status ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}
