package by.epam.pizzashop.controller.command.impl.customer;

import by.epam.pizzashop.controller.command.*;

import by.epam.pizzashop.dto.RestaurantDto;
import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.RestaurantService;
import by.epam.pizzashop.model.service.impl.RestaurantServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The type Go to order page command.
 */
public class GoToOrderPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = 0;
        HttpSession session = request.getSession();
        Map<Product, Integer> products = (Map<Product, Integer>) session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET);
        if(products == null) {
            request.setAttribute(RequestAttribute.NEED_CHOOSE_PRODUCTS_ERROR, BundleKey.NEED_CHOOSE_PRODUCTS_ERROR);
            return new CommandResult(PagePath.MAIN_CUSTOMER, CommandResult.RoutingType.FORWARD);
        }
        removeIfProductQuantityIsZero(products);
        RestaurantService restaurantService = RestaurantServiceImpl.getInstance();
        String restaurantId = request.getParameter(RequestParameter.RESTAURANT_ID);
        if (session.getAttribute(SessionAttribute.PHARMACY_ID) == null && restaurantId == null) {
            return new CommandResult(PagePath.RESTAURANTS_FOR_CUSTOMER, CommandResult.RoutingType.REDIRECT);
        }

        if (restaurantId != null) {
            id = Long.parseLong(restaurantId);
            session.setAttribute(SessionAttribute.PHARMACY_ID, id);
        }

        if (session.getAttribute(SessionAttribute.PHARMACY_ID) != null) {
            id = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        }

        RestaurantDto restaurant;

        try {
            restaurant = restaurantService.findRestaurantById(id);
            session.setAttribute(SessionAttribute.RESTAURANT_ORDER, restaurant);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute while find pharmacy by id ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return new CommandResult(PagePath.ORDER, CommandResult.RoutingType.REDIRECT);
    }

    private void removeIfProductQuantityIsZero(Map<Product, Integer> products) {
        products.values().remove(0);
    }
}
