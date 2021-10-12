package by.epam.pizzashop.controller.command.impl.manager;

import by.epam.pizzashop.controller.command.Command;
import by.epam.pizzashop.controller.command.CommandResult;
import by.epam.pizzashop.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to manager main page command.
 */
public class GoToManagerMainPageCommand  implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.MAIN_MANAGER, CommandResult.RoutingType.REDIRECT);
    }
}
