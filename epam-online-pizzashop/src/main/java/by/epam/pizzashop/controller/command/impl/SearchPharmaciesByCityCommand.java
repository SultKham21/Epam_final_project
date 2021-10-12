package by.epam.pizzashop.controller.command.impl;

import by.epam.pizzashop.controller.command.*;

import by.epam.pizzashop.dto.RestaurantDto;
import by.epam.pizzashop.entity.Role;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.RestaurantService;
import by.epam.pizzashop.model.service.impl.RestaurantServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Search pharmacies by city command.
 */
public class SearchPharmaciesByCityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult commandResult;
        HttpSession session = request.getSession();
        String city = request.getParameter(RequestParameter.CITY_FOR_SEARCH_PHARMACIES);
        RestaurantService restaurantService = RestaurantServiceImpl.getInstance();
        List<RestaurantDto> restaurants;
        try {
            restaurants = restaurantService.findListRestaurantsByCity(city);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list restaurants by city ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.LIST_PHARMACIES_BY_CITY, restaurants);
        User auth = (User) session.getAttribute(SessionAttribute.USER_AUTH);
        Role role = auth.getRole();
        if (role.equals(Role.CUSTOMER)) {
            commandResult = new CommandResult(PagePath.RESTAURANTS_FOR_CUSTOMER, CommandResult.RoutingType.REDIRECT);
        } else {
            commandResult = new CommandResult(PagePath.MAIN_MANAGER, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}
