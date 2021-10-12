package by.epam.pizzashop.controller.command.impl.admin;

import by.epam.pizzashop.controller.command.*;

import by.epam.pizzashop.dto.RestaurantDto;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.RestaurantService;
import by.epam.pizzashop.model.service.impl.RestaurantServiceImpl;
import by.epam.pizzashop.model.validation.RestaurantValidator;
import by.epam.pizzashop.model.validation.impl.RestaurantValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Updating restaurant house command.
 */
public class UpdatingRestaurantHouseCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 15;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int currentPage = (int)session.getAttribute(SessionAttribute.CURRENT_PAGE);
        String newHouse = request.getParameter(RequestParameter.UPDATING_RESTAURANT_HOUSE);
        long id = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        RestaurantService restaurantService = RestaurantServiceImpl.getInstance();
        RestaurantValidator restaurantValidator = RestaurantValidatorImpl.getInstance();
        List<RestaurantDto> currentRestaurants;
        List<RestaurantDto> nextRestaurants;
        List<RestaurantDto> previousRestaurants = new ArrayList<>();

        if (!restaurantValidator.isValidHouse(newHouse)) {
            request.setAttribute(RequestAttribute.UPDATING_RESTAURANT_HOUSE_ERROR, BundleKey.PHARMACY_HOUSE_ERROR);
            return new CommandResult(PagePath.UPDATING_RESTAURANT_HOUSE, CommandResult.RoutingType.FORWARD);
        }
        try {
            restaurantService.updateHouse(id, newHouse);
            if (currentPage != 1) {
                previousRestaurants = restaurantService.findListRestaurants((currentPage - 2) * RECORD_PER_PAGE);
            }
            currentRestaurants = restaurantService.findListRestaurants((currentPage - 1) * RECORD_PER_PAGE);
            nextRestaurants = restaurantService.findListRestaurants((currentPage) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while update house or find all current pharmacies ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.PREVIOUS_RESTAURANTS, previousRestaurants);
        session.setAttribute(SessionAttribute.NEXT_RESTAURANTS, nextRestaurants);
        session.setAttribute(SessionAttribute.CURRENT_PHARMACIES, currentRestaurants);
        return new CommandResult(PagePath.ALL_RESTAURANTS, CommandResult.RoutingType.REDIRECT);
    }
}
