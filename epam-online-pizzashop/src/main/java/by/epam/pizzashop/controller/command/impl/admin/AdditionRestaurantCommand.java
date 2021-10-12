package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.*;
import by.epam.pizzashop.dto.RestaurantDto;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.RestaurantService;
import by.epam.pizzashop.model.service.impl.RestaurantServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Addition restaurant command.
 */
public class AdditionRestaurantCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 15;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RestaurantService restaurantService = RestaurantServiceImpl.getInstance();
        String number = request.getParameter(RequestParameter.NUMBER);
        String city = request.getParameter(RequestParameter.CITY);
        String street = request.getParameter(RequestParameter.STREET);
        String house = request.getParameter(RequestParameter.HOUSE);
        String block = request.getParameter(RequestParameter.BLOCK);

        Map<String, String> dataRestaurant = restaurantService.isFormValid(number, city, street, house, block);


        if (dataRestaurant.get(RequestParameter.NUMBER).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, dataRestaurant);
            request.setAttribute(RequestAttribute.RESTAURANT_NUMBER_ERROR, BundleKey.PHARMACY_NUMBER_ERROR);
            return new CommandResult(PagePath.ALL_RESTAURANTS, CommandResult.RoutingType.FORWARD);
        }

        if (dataRestaurant.get(RequestParameter.CITY).isBlank() || dataRestaurant.get(RequestParameter.STREET).isBlank() ||
                dataRestaurant.get(RequestParameter.HOUSE).isBlank()) {
            request.setAttribute(RequestAttribute.MAP_DATA, dataRestaurant);
            request.setAttribute(RequestAttribute.RESTAURANT_STRING_PARAMETERS_ERROR, BundleKey.PHARMACY_STRING_PARAMETERS_ERROR);
            return new CommandResult(PagePath.ALL_RESTAURANTS, CommandResult.RoutingType.FORWARD);
        }

        if (dataRestaurant.get(RequestParameter.BLOCK).isEmpty()) {
            request.setAttribute(RequestAttribute.MAP_DATA, dataRestaurant);
            request.setAttribute(RequestAttribute.RESTAURANT_BLOCK_ERROR, BundleKey.PHARMACY_BLOCK_ERROR);
            return new CommandResult(PagePath.ALL_RESTAURANTS, CommandResult.RoutingType.FORWARD);
        }

        int currentPage;
        List<RestaurantDto> currentRestaurants;
        List<RestaurantDto> previousRestaurants;
        List<RestaurantDto> nextRestaurants = new ArrayList<>();
        try {
            currentRestaurants = restaurantService.createRestaurant(number, city, street, house, block);
            currentPage = restaurantService.findCurrentPage();
            previousRestaurants = restaurantService.findListRestaurants((currentPage - 2) * RECORD_PER_PAGE);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find  current restaurants ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.CURRENT_PHARMACIES, currentRestaurants);
        session.setAttribute(SessionAttribute.PREVIOUS_RESTAURANTS, previousRestaurants);
        session.setAttribute(SessionAttribute.NEXT_RESTAURANTS, nextRestaurants);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return new CommandResult(PagePath.ALL_RESTAURANTS, CommandResult.RoutingType.REDIRECT);
    }
}
