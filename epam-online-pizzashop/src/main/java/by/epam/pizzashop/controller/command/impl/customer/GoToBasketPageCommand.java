package by.epam.pizzashop.controller.command.impl.customer;

import by.epam.pizzashop.controller.command.Command;
import by.epam.pizzashop.controller.command.CommandResult;
import by.epam.pizzashop.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to basket page command.
 */
public class GoToBasketPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.BASKET, CommandResult.RoutingType.REDIRECT);
    }
}
