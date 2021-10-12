package by.epam.pizzashop.controller.command.impl;

import by.epam.pizzashop.controller.command.Command;
import by.epam.pizzashop.controller.command.CommandResult;
import by.epam.pizzashop.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Logout command.
 */
public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
    }
}
