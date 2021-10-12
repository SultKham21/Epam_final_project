package by.epam.pizzashop.controller.command.impl.customer;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.dto.ProductDto;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.ProductService;
import by.epam.pizzashop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Search products by name command.
 */
public class SearchProductsByNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.LIST_PRODUCTS_BY_NAME);
        String productName = request.getParameter(RequestParameter.NAME_FOR_SEARCH_PRODUCTS);
        ProductService productService = ProductServiceImpl.getInstance();
        List<ProductDto> products;
        try {
            products = productService.findListProductsByName(productName);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list products by name ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        if(products.isEmpty()) {
            request.setAttribute(RequestAttribute.NO_SUCH_PRODUCTS_IN_SEARCH, BundleKey.NO_SUCH_PRODUCTS_IN_SEARCH);
            return new CommandResult(PagePath.SEARCH_PRODUCTS_BY_NAME, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.LIST_PRODUCTS_BY_NAME, products);
        return new CommandResult(PagePath.SEARCH_PRODUCTS_BY_NAME, CommandResult.RoutingType.REDIRECT);
    }

}
