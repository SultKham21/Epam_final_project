package by.epam.pizzashop.model.service.impl;

import by.epam.pizzashop.controller.command.RequestParameter;

import by.epam.pizzashop.dto.RestaurantDto;
import by.epam.pizzashop.entity.Restaurant;
import by.epam.pizzashop.exception.DaoException;
import by.epam.pizzashop.exception.ServiceException;

import by.epam.pizzashop.model.dao.RestaurantDao;
import by.epam.pizzashop.model.dao.impl.RestaurantDaoImpl;
import by.epam.pizzashop.model.service.RestaurantService;
import by.epam.pizzashop.model.validation.RestaurantValidator;
import by.epam.pizzashop.model.validation.impl.RestaurantValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.epam.pizzashop.controller.command.RequestParameter.*;


/**
 * The type Restaurant service.
 */
public class RestaurantServiceImpl implements RestaurantService {
    private Logger logger = LogManager.getLogger();
    private static final String ZERO_STRING = "0";
    private static final String BLANK_STRING = "\s";
    private static final int RECORD_PER_PAGE = 15;
    private RestaurantDao restaurantDao = RestaurantDaoImpl.getInstance();
    private RestaurantValidator restaurantValidator = RestaurantValidatorImpl.getInstance();

    private RestaurantServiceImpl() {
    }

    private static RestaurantServiceImpl instance = new RestaurantServiceImpl();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RestaurantServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<RestaurantDto> findListRestaurants(int startingRestaurant) throws ServiceException {
        List<Restaurant> restaurantsDb;
        try {
            restaurantsDb = restaurantDao.findRestaurants(startingRestaurant);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListPharmacies() ", e);
            throw new ServiceException("DaoException is in method findListPharmacies() ", e);
        }

        List<RestaurantDto> restaurants = convertListRestaurantListToRestaurantDto(restaurantsDb);
        return restaurants;
    }

    @Override
    public List<RestaurantDto> findListRestaurantsByCity(String city) throws ServiceException {
        List<Restaurant> restaurantsDb;
        try {
            restaurantsDb = restaurantDao.findRestaurantsByCity(city);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListRestaurantsByCity() ", e);
            throw new ServiceException("DaoException is in method findListRestaurantsByCity() ", e);
        }

        List<RestaurantDto> restaurants = convertListRestaurantListToRestaurantDto(restaurantsDb);
        return restaurants;
    }

    @Override
    public List<RestaurantDto> createRestaurant(String number, String city, String street, String house, String block) throws ServiceException {
        List<Restaurant> currentRestaurants;
        Restaurant restaurant = new Restaurant.Builder()
                .setNumber(Integer.parseInt(number))
                .setCity(city)
                .setStreet(street)
                .setHouse(house)
                .build();
        if (block.isBlank()) {
            restaurant.setBlock(0);
        } else {
            restaurant.setBlock(Integer.valueOf(block));
        }
        try {
            restaurantDao.createRestaurant(restaurant);
            int restaurantsNumber = restaurantDao.findRestaurantsNumber();
            int restaurantsOnLastPage = restaurantsNumber % RECORD_PER_PAGE;
            int pages = restaurantsNumber / RECORD_PER_PAGE;
            if (restaurantsOnLastPage == 0) {
                currentRestaurants = restaurantDao.findRestaurants(pages * RECORD_PER_PAGE - RECORD_PER_PAGE);
            } else {
                currentRestaurants = restaurantDao.findRestaurants(pages * RECORD_PER_PAGE);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createRestaurant() ", e);
            throw new ServiceException("DaoException is in method createRestaurant() ", e);
        }
        List<RestaurantDto> restaurants = convertListRestaurantListToRestaurantDto(currentRestaurants);
        return restaurants;
    }

    @Override
    public int findCurrentPage() throws ServiceException {
        int restaurantsNumber;
        int currentPage;
        try {
            restaurantsNumber = restaurantDao.findRestaurantsNumber();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findCurrentPage() ", e);
            throw new ServiceException("DaoException is in method findCurrentPage() ", e);
        }
        if (restaurantsNumber % RECORD_PER_PAGE == 0) {
            currentPage = restaurantsNumber / RECORD_PER_PAGE;
        } else {
            currentPage = restaurantsNumber / RECORD_PER_PAGE + 1;
        }
        return currentPage;
    }

    @Override
    public Map<String, String> isFormValid(String number, String city, String street, String house, String block) {
        Map<String, String> restaurantParameters = new HashMap<>();
        restaurantParameters.put(NUMBER, number);
        restaurantParameters.put(CITY, city);
        restaurantParameters.put(STREET, street);
        restaurantParameters.put(HOUSE, house);
        restaurantParameters.put(BLOCK, block);
        restaurantValidator.isValidForm(restaurantParameters);

        if (restaurantParameters.get(RequestParameter.BLOCK).equals(ZERO_STRING)) {
            restaurantParameters.put(RequestParameter.BLOCK, BLANK_STRING);
        }
        return restaurantParameters;
    }

    @Override
    public RestaurantDto findRestaurantById(long id) throws ServiceException {
        Restaurant restaurantDb;
        try {
            restaurantDb = restaurantDao.findRestaurantById(id).orElse(new Restaurant());
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findRestaurantById() ", e);
            throw new ServiceException("DaoException is in method findRestaurantById() ", e);
        }
        RestaurantDto restaurant = convertRestaurantToRestaurantDto(restaurantDb);
        return restaurant;
    }



    @Override
    public void updateNumber(long id, String number) throws ServiceException {
        int newNumber = Integer.parseInt(number);
        try {
            restaurantDao.updateNumber(id, newNumber);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateNumber() ", e);
            throw new ServiceException("DaoException is in method updateNumber() ", e);
        }
    }

    @Override
    public void updateCity(long id, String city) throws ServiceException {
        try {
            restaurantDao.updateCity(id, city);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateCity() ", e);
            throw new ServiceException("DaoException is in method updateCity() ", e);
        }
    }

    @Override
    public void updateStreet(long id, String street) throws ServiceException {
        try {
            restaurantDao.updateStreet(id, street);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateStreet() ", e);
            throw new ServiceException("DaoException is in method updateStreet() ", e);
        }
    }

    @Override
    public void updateHouse(long id, String house) throws ServiceException {
        try {
            restaurantDao.updateHouse(id, house);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateHouse() ", e);
            throw new ServiceException("DaoException is in method updateHouse() ", e);
        }
    }

    @Override
    public void updateBlock(long id, String block) throws ServiceException {
        if (block.isBlank()) {
            try {
                restaurantDao.updateBlock(id, 0);
            } catch (DaoException e) {
                logger.log(Level.ERROR, "DaoException is in method updateBlock() ", e);
                throw new ServiceException("DaoException is in method updateBlock() ", e);
            }
            return;
        }

        int newBlock = Integer.parseInt(block);
        try {
            restaurantDao.updateBlock(id, newBlock);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateBlock() ", e);
            throw new ServiceException("DaoException is in method updateBlock() ", e);
        }
    }

    private String convertBlock(Integer block) {
        return (block == 0) ? null : String.valueOf(block);
    }

    private List<RestaurantDto> convertListRestaurantListToRestaurantDto(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> new RestaurantDto.Builder()
                        .setRestaurantId(restaurant.getRestaurantId())
                        .setNumber(restaurant.getNumber())
                        .setCity(restaurant.getCity())
                        .setStreet(restaurant.getStreet())
                        .setHouse(restaurant.getHouse())
                        .setBlock(convertBlock(restaurant.getBlock()))
                        .build()).collect(Collectors.toList());
    }

    private RestaurantDto convertRestaurantToRestaurantDto(Restaurant restaurant) {
        return  new RestaurantDto.Builder()
                .setRestaurantId(restaurant.getRestaurantId())
                .setNumber(restaurant.getNumber())
                .setCity(restaurant.getCity())
                .setStreet(restaurant.getStreet())
                .setHouse(restaurant.getHouse())
                .setBlock(convertBlock(restaurant.getBlock()))
                .build();
    }

}
