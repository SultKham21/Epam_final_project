package by.epam.pizzashop.controller.command.impl.manager;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.entity.Basket;
import by.epam.pizzashop.entity.Order;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.OrderService;
import by.epam.pizzashop.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Basket for order command.
 */
public class BasketForOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        OrderService orderService = OrderServiceImpl.getInstance();
        List<Basket> basket;
        Order order;
        try {
            basket = orderService.findBasketForOrder(orderId);
            order = orderService.findOrderById(orderId);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find all products in order or find order", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.BASKET, basket);
        session.setAttribute(SessionAttribute.ORDER, order);
        return new CommandResult(PagePath.BASKET_FOR_ORDER, CommandResult.RoutingType.REDIRECT);
    }
}
