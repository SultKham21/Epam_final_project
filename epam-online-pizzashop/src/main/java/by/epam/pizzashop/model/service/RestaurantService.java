package by.epam.pizzashop.model.service;


import by.epam.pizzashop.dto.RestaurantDto;
import by.epam.pizzashop.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface Restaurant service.
 */
public interface RestaurantService {
    /**
     * Find list restaurants list.
     *
     * @param startingRestaurant the starting restaurant
     * @return the list
     * @throws ServiceException the service exception
     */
    List<RestaurantDto> findListRestaurants(int startingRestaurant) throws ServiceException;

    /**
     * Find list restaurants by city list.
     *
     * @param city the city
     * @return the list
     * @throws ServiceException the service exception
     */
    List<RestaurantDto> findListRestaurantsByCity(String city) throws ServiceException;

    /**
     * Create restaurant list.
     *
     * @param number the number
     * @param city   the city
     * @param street the street
     * @param house  the house
     * @param block  the block
     * @return the list
     * @throws ServiceException the service exception
     */
    List<RestaurantDto> createRestaurant(String number, String city, String street, String house, String block) throws ServiceException;

    /**
     * Find current page int.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int findCurrentPage() throws ServiceException;

    /**
     * Is form valid map.
     *
     * @param number the number
     * @param city   the city
     * @param street the street
     * @param house  the house
     * @param block  the block
     * @return the map
     */
    Map<String, String> isFormValid(String number, String city, String street, String house, String block);

    /**
     * Find restaurant by id restaurant dto.
     *
     * @param id the id
     * @return the restaurant dto
     * @throws ServiceException the service exception
     */
    RestaurantDto findRestaurantById(long id) throws ServiceException;

    /**
     * Update number.
     *
     * @param id     the id
     * @param number the number
     * @throws ServiceException the service exception
     */
    void updateNumber(long id, String number) throws ServiceException;

    /**
     * Update city.
     *
     * @param id   the id
     * @param city the city
     * @throws ServiceException the service exception
     */
    void updateCity(long id, String city) throws ServiceException;

    /**
     * Update street.
     *
     * @param id     the id
     * @param street the street
     * @throws ServiceException the service exception
     */
    void updateStreet(long id, String street) throws ServiceException;

    /**
     * Update house.
     *
     * @param id    the id
     * @param house the house
     * @throws ServiceException the service exception
     */
    void updateHouse(long id, String house) throws ServiceException;

    /**
     * Update block.
     *
     * @param id    the id
     * @param block the block
     * @throws ServiceException the service exception
     */
    void updateBlock(long id, String block) throws ServiceException;
}
