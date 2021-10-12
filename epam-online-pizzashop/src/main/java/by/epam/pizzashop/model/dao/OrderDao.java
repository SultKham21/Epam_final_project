package by.epam.pizzashop.model.dao;

import by.epam.pizzashop.entity.Basket;
import by.epam.pizzashop.entity.Order;
import by.epam.pizzashop.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order dao.
 */
public interface OrderDao {
    /**
     * Create order order.
     *
     * @param order the order
     * @return the order
     * @throws DaoException the dao exception
     */
    Order createOrder(Order order) throws DaoException;

    /**
     * Create products in basket.
     *
     * @param basket the basket
     * @throws DaoException the dao exception
     */
    void createProductsInBasket(List<Basket> basket) throws DaoException;

    /**
     * Find all processing orders for pharmacies list.
     *
     * @param restaurantId the restaurant id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllProcessingOrdersForPharmacies(long restaurantId) throws DaoException;

    /**
     * Find basket for order list.
     *
     * @param orderId the order id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Basket> findBasketForOrder(long orderId) throws DaoException;

    /**
     * Find order by id optional.
     *
     * @param orderId the order id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Order> findOrderById(long orderId) throws DaoException;

    /**
     * Update status order.
     *
     * @param statusId the status id
     * @param orderId  the order id
     * @throws DaoException the dao exception
     */
    void updateStatusOrder(int statusId, long orderId) throws DaoException;

}
