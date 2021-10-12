package by.epam.pizzashop.controller.command.impl.customer;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.ProductService;
import by.epam.pizzashop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Addition product to order command.
 */
public class AdditionProductToOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        String url = request.getParameter(RequestParameter.CURRENT_URL);

        Map<Product, Integer> products;
        if (session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET) != null) {
            products = (Map<Product, Integer>) session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET);
        } else {
            products = new LinkedHashMap<>();
        }
        ProductService productService = ProductServiceImpl.getInstance();
        try {
            products = productService.addProductToOrder(id, products);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute add product to order ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET, products);
        return new CommandResult(url, CommandResult.RoutingType.REDIRECT);
    }
}
