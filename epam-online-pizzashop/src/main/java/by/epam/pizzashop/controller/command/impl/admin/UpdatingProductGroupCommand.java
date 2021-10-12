package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.dto.ProductDto;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.ProductService;
import by.epam.pizzashop.model.service.impl.ProductServiceImpl;
import by.epam.pizzashop.model.validation.ProductValidator;
import by.epam.pizzashop.model.validation.impl.ProductValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Updating product group command.
 */
public class UpdatingProductGroupCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 5;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int currentPage = (int)session.getAttribute(SessionAttribute.CURRENT_PAGE);
        long productId = (long) session.getAttribute(SessionAttribute.PRODUCT_ID);
        String newGroup = request.getParameter(RequestParameter.UPDATING_PRODUCT_GROUP);
        ProductService productService = ProductServiceImpl.getInstance();
        ProductValidator productValidator = ProductValidatorImpl.getInstance();

        List<ProductDto> currentProducts;
        List<ProductDto> nextProducts;
        List<ProductDto> previousProducts = new ArrayList<>();

        if(!productValidator.isValidStringParameters(newGroup)) {
            request.setAttribute(RequestAttribute.PRODUCT_GROUP_ERROR, BundleKey.PRODUCT_GROUP_ERROR);
            return new CommandResult(PagePath.UPDATING_PRODUCT_GROUP, CommandResult.RoutingType.FORWARD);
        }

        try {
            productService.updateProductGroup(productId, newGroup);
            if (currentPage != 1) {
                previousProducts = productService.findListProducts((currentPage - 2) * RECORD_PER_PAGE);
            }
            currentProducts = productService.findListProducts((currentPage - 1) * RECORD_PER_PAGE);
            nextProducts = productService.findListProducts((currentPage) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list current products or update group of product", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.PREVIOUS_PRODUCTS, previousProducts);
        session.setAttribute(SessionAttribute.NEXT_PRODUCTS, nextProducts);
        session.setAttribute(SessionAttribute.CURRENT_PRODUCTS, currentProducts);
        return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.REDIRECT);
    }
}

