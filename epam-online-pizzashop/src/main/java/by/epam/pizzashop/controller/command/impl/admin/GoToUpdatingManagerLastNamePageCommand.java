package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to updating manager last name page command.
 */
public class GoToUpdatingManagerLastNamePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.USER_ID);
        long managerId = Long.parseLong(id);
        request.getSession().setAttribute(SessionAttribute.manager_ID, managerId);
        return new CommandResult(PagePath.UPDATING_MANAGER_LAST_NAME, CommandResult.RoutingType.REDIRECT);
    }
}