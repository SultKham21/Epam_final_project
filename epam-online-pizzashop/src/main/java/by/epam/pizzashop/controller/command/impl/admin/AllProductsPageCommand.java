package by.epam.pizzashop.controller.command.impl.admin;

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
import java.util.ArrayList;
import java.util.List;

/**
 * The type All products page command.
 */
public class AllProductsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 5;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int currentPage = (int) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        String countForwardParameter = request.getParameter(RequestParameter.COUNT_FORWARD);
        String countBackParameter = request.getParameter(RequestParameter.COUNT_BACK);
        boolean countForward = Boolean.parseBoolean(countForwardParameter);
        boolean countBack = Boolean.parseBoolean(countBackParameter);

        if (countForward) {
            currentPage += 1;
        }

        if (countBack) {
            currentPage -= 1;
        }

        ProductService productService = ProductServiceImpl.getInstance();
        List<ProductDto> products;
        List<ProductDto> nextProducts;
        List<ProductDto> previousProducts = new ArrayList<>();
        try {

            if (currentPage != 1) {
                previousProducts = productService.findListProducts((currentPage - 2) * RECORD_PER_PAGE);
            }
            products = productService.findListProducts((currentPage - 1) * RECORD_PER_PAGE);
            nextProducts = productService.findListProducts((currentPage) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list products ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        session.setAttribute(SessionAttribute.NEXT_PRODUCTS, nextProducts);
        session.setAttribute(SessionAttribute.PREVIOUS_PRODUCTS, previousProducts);
        session.setAttribute(SessionAttribute.CURRENT_PRODUCTS, products);
        return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.REDIRECT);
    }
}
