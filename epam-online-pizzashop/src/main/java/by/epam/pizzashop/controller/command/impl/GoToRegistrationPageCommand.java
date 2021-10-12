package by.epam.pizzashop.controller.command.impl;

import by.epam.pizzashop.controller.command.Command;
import by.epam.pizzashop.controller.command.CommandResult;
import by.epam.pizzashop.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to registration page command.
 */
public class GoToRegistrationPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
      return new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.REDIRECT);
    }
}
