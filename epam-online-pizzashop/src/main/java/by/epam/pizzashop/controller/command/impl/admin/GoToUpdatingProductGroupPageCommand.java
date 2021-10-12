package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to updating product group page command.
 */
public class GoToUpdatingProductGroupPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        long id = Long.parseLong(productId);
        request.getSession().setAttribute(SessionAttribute.PRODUCT_ID, id);
        return new CommandResult(PagePath.UPDATING_PRODUCT_GROUP, CommandResult.RoutingType.REDIRECT);
    }
}
