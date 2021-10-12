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
import java.util.Map;


/**
 * The type Addition product command.
 */
public class AdditionProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 5;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ProductService productService = ProductServiceImpl.getInstance();
        String name = request.getParameter(RequestParameter.NAME);
        String dose = request.getParameter(RequestParameter.DOSE);
        String group = request.getParameter(RequestParameter.GROUP);
        String price = request.getParameter(RequestParameter.PRICE);
        String instruction = request.getParameter(RequestParameter.INSTRUCTION);

        Map<String, String> productParameters = productService.isValidParameters(name, dose, group, price, instruction);
        if (productParameters.get(RequestParameter.NAME).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_NAME_ERROR, BundleKey.PRODUCT_NAME_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }



        if (productParameters.get(RequestParameter.DOSE).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_DOSE_ERROR, BundleKey.PRODUCT_DOSE_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }


        if (productParameters.get(RequestParameter.GROUP).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_GROUP_ERROR, BundleKey.PRODUCT_GROUP_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }

        if (productParameters.get(RequestParameter.PRICE).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_PRICE_ERROR, BundleKey.PRODUCT_PRICE_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }

        if (productParameters.get(RequestParameter.INSTRUCTION).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, productParameters);
            request.setAttribute(RequestAttribute.PRODUCT_INSTRUCTION_ERROR, BundleKey.PRODUCT_INSTRUCTION_ERROR);
            return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.FORWARD);
        }

        int currentPage;
        List<ProductDto> currentProducts;
        List<ProductDto> nextProducts = new ArrayList<>();
        List<ProductDto> previousProducts;

        try {
            currentProducts = productService.createProduct(name, dose, group,price, instruction);
            currentPage = productService.findCurrentPage();
            previousProducts = productService.findListProducts((currentPage - 2) * RECORD_PER_PAGE);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find current products ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.CURRENT_PRODUCTS, currentProducts);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        session.setAttribute(SessionAttribute.PREVIOUS_PRODUCTS, previousProducts);
        session.setAttribute(SessionAttribute.NEXT_PRODUCTS, nextProducts);
        return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.REDIRECT);
    }
}
