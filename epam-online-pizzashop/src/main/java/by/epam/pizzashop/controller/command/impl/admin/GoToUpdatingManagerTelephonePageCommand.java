package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to updating manager telephone page command.
 */
public class GoToUpdatingManagerTelephonePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.USER_ID);
        long managerId = Long.parseLong(id);
        request.getSession().setAttribute(SessionAttribute.manager_ID, managerId);
        return new CommandResult(PagePath.UPDATING_MANAGER_TELEPHONE, CommandResult.RoutingType.REDIRECT);
    }
}
