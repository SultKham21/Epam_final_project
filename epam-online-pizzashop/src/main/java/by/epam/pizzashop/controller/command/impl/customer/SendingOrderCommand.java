package by.epam.pizzashop.controller.command.impl.customer;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.OrderService;
import by.epam.pizzashop.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The type Sending order command.
 */
public class SendingOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderService orderService = OrderServiceImpl.getInstance();
        long restaurantId = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        User auth =(User) session.getAttribute(SessionAttribute.USER_AUTH);
        Map<Product, Integer> products = (Map<Product, Integer>) session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET);
        if (products.isEmpty()) {
            request.setAttribute(RequestAttribute.BASKET_IS_EMPTY_ERROR, BundleKey.BASKET_IS_EMPTY_ERROR);
            return new CommandResult(PagePath.ORDER, CommandResult.RoutingType.FORWARD);
        }
        try {
            orderService.createOrder(restaurantId, auth, products);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while create order ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
       return new CommandResult(PagePath.ORDER_ACCEPT, CommandResult.RoutingType.REDIRECT);
    }
}
