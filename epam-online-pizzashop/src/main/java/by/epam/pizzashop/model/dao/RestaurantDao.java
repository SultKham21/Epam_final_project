package by.epam.pizzashop.model.dao;


import by.epam.pizzashop.entity.Restaurant;
import by.epam.pizzashop.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Restaurant dao.
 */
public interface RestaurantDao {
    /**
     * Find restaurants list.
     *
     * @param startingRestaurant the starting restaurant
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Restaurant> findRestaurants(int startingRestaurant) throws DaoException;

    /**
     * Find restaurants by city list.
     *
     * @param city the city
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Restaurant> findRestaurantsByCity(String city) throws DaoException;

    /**
     * Create restaurant.
     *
     * @param restaurant the restaurant
     * @throws DaoException the dao exception
     */
    void createRestaurant(Restaurant restaurant) throws DaoException;

    /**
     * Find restaurant by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Restaurant> findRestaurantById(long id) throws DaoException;

    /**
     * Find restaurants number int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int findRestaurantsNumber() throws DaoException ;

    /**
     * Update number.
     *
     * @param id     the id
     * @param number the number
     * @throws DaoException the dao exception
     */
    void updateNumber(long id, int number) throws DaoException;

    /**
     * Update city.
     *
     * @param id   the id
     * @param city the city
     * @throws DaoException the dao exception
     */
    void updateCity(long id, String city) throws DaoException;

    /**
     * Update street.
     *
     * @param id     the id
     * @param street the street
     * @throws DaoException the dao exception
     */
    void updateStreet(long id, String street) throws DaoException;

    /**
     * Update house.
     *
     * @param id    the id
     * @param house the house
     * @throws DaoException the dao exception
     */
    void updateHouse(long id, String house) throws DaoException;

    /**
     * Update block.
     *
     * @param id    the id
     * @param block the block
     * @throws DaoException the dao exception
     */
    void updateBlock(long id, int block) throws DaoException;

}
