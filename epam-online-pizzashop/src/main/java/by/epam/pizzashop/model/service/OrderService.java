package by.epam.pizzashop.model.service;

import by.epam.pizzashop.entity.Basket;
import by.epam.pizzashop.entity.Order;
import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Create order.
     *
     * @param restaurantId the restaurant id
     * @param auth         the auth
     * @param products     the products
     * @throws ServiceException the service exception
     */
    void createOrder(long restaurantId, User auth, Map<Product, Integer> products) throws ServiceException;

    /**
     * Find all processing orders for pharmacies list.
     *
     * @param restaurantId the restaurant id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllProcessingOrdersForPharmacies(String restaurantId) throws ServiceException;

    /**
     * Find basket for order list.
     *
     * @param orderId the order id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Basket> findBasketForOrder(String orderId) throws ServiceException;

    /**
     * Find order by id order.
     *
     * @param orderId the order id
     * @return the order
     * @throws ServiceException the service exception
     */
    Order findOrderById(String orderId) throws ServiceException;

    /**
     * Update status order order.
     *
     * @param statusOrderId the status order id
     * @param orderId       the order id
     * @param basket        the basket
     * @return the order
     * @throws ServiceException the service exception
     */
    Order updateStatusOrder(String statusOrderId, String orderId, Basket basket) throws ServiceException;
}
