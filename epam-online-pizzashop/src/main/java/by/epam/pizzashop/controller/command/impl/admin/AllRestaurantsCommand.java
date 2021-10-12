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

/**
 * The type All restaurants command.
 */
public class AllRestaurantsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 15;

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

        RestaurantService restaurantService = RestaurantServiceImpl.getInstance();
        List<RestaurantDto> restaurants;
        List<RestaurantDto> nextRestaurants;
        List<RestaurantDto> previousRestaurants = new ArrayList<>();
        try {

            if (currentPage != 1) {
                previousRestaurants = restaurantService.findListRestaurants((currentPage - 2) * RECORD_PER_PAGE);
            }
            restaurants = restaurantService.findListRestaurants((currentPage - 1) * RECORD_PER_PAGE);
            nextRestaurants = restaurantService.findListRestaurants((currentPage) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list restaurants ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        session.setAttribute(SessionAttribute.NEXT_RESTAURANTS, nextRestaurants);
        session.setAttribute(SessionAttribute.PREVIOUS_RESTAURANTS, previousRestaurants);
        session.setAttribute(SessionAttribute.CURRENT_PHARMACIES, restaurants);
        return new CommandResult(PagePath.ALL_RESTAURANTS, CommandResult.RoutingType.REDIRECT);
    }
}
