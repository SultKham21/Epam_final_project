package by.epam.pizzashop.model.service.impl;

import by.epam.pizzashop.entity.*;
import by.epam.pizzashop.exception.DaoException;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.dao.OrderDao;
import by.epam.pizzashop.model.dao.RestaurantDao;
import by.epam.pizzashop.model.dao.UserDao;
import by.epam.pizzashop.model.dao.impl.OrderDaoImpl;
import by.epam.pizzashop.model.dao.impl.RestaurantDaoImpl;
import by.epam.pizzashop.model.dao.impl.UserDaoImpl;
import by.epam.pizzashop.model.service.OrderService;
import by.epam.pizzashop.model.verification.EmailSending;
import by.epam.pizzashop.model.verification.impl.EmailSendingImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Order service.
 */
public class OrderServiceImpl implements OrderService {
    private Logger logger = LogManager.getLogger();
    private static final String HEADER_FOR_PREPARED_ORDER = "Information about your order";
    private static final String MESSAGE_FOR_PREPARED_ORDER = """
            Hello, %s  %s! Your order number %s is prepared.
            """;
    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private RestaurantDao restaurantDao = RestaurantDaoImpl.getInstance();

    private EmailSending emailSending = EmailSendingImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    private static OrderServiceImpl instance = new OrderServiceImpl();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OrderServiceImpl getInstance() {
        return instance;
    }


    @Override
    public void createOrder(long restaurantId, User auth, Map<Product, Integer> products) throws ServiceException {
        Optional<Restaurant> restaurantDb;
        try {
         restaurantDb = restaurantDao.findRestaurantById(restaurantId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPharmacyByIdr() ", e);
            throw new ServiceException("DaoException is in method findPharmacyById() ", e);
        }
        //TODO
        Restaurant restaurant = restaurantDb.orElse(new Restaurant());
        Order order = new Order.Builder()
                .setDataStarting(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())))
                .setDataEnding(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).plusHours(24)))
                .setRestaurant(restaurant)
                .build();
        Order orderDB;
        try {
            orderDB = orderDao.createOrder(order);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createOrder() ", e);
            throw new ServiceException("DaoException is in method createOrder() ", e);
        }
        try {
            List<Basket> basketList = new ArrayList<>();
            products.entrySet().forEach(product -> basketList.add(new Basket.Builder()
                    .setUser(auth)
                    .setProduct(product.getKey())
                    .setOrder(orderDB)
                    .setQuantity(product.getValue())
                    .build()));
            orderDao.createProductsInBasket(basketList);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createProductsInBasket() ", e);
            throw new ServiceException("DaoException is in method createProductsInBasket() ", e);
        }
    }

    @Override
    public List<Order> findAllProcessingOrdersForPharmacies(String restaurantId) throws ServiceException {
        long id = Long.parseLong(restaurantId);
        List<Order> orders;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
         orders = orderDao.findAllProcessingOrdersForPharmacies(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllProcessingOrdersForPharmacies() ", e);
            throw new ServiceException("DaoException is in method findAllProcessingOrdersForPharmacies() ", e);
        }
        return orders;
    }

    @Override
    public List<Basket> findBasketForOrder(String orderId) throws ServiceException {
        long id = Long.parseLong(orderId);
        List<Basket> basket;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            basket = orderDao.findBasketForOrder(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findAllProductsInOrder() ", e);
            throw new ServiceException("DaoException is in method findAllProductsInOrder() ", e);
        }
        return basket;
    }

    @Override
    public Order findOrderById(String orderId) throws ServiceException {
        long id = Long.parseLong(orderId);
        Optional<Order> orderDb;
        try {
           orderDb = orderDao.findOrderById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findOrderById() ", e);
            throw new ServiceException("DaoException is in method findOrderById() ", e);
        }
        Order order = orderDb.orElse(new Order());
        return order;
    }

    @Override
    public Order updateStatusOrder(String statusOrderId, String orderId, Basket basket) throws ServiceException {
        int statusOrder = Integer.parseInt(statusOrderId);
        long id = Long.parseLong(orderId);
        Optional<Order> orderDb;
        try {
            orderDao.updateStatusOrder(statusOrder, id);
            orderDb = orderDao.findOrderById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method  updateStatusOrder() ", e);
            throw new ServiceException("DaoException is in method updateStatusOrder() ", e);
        }
        Order order = orderDb.orElse(new Order());
        if (statusOrder == 2) {
            User whoDidOrder = basket.getUser();
            sendConfirmingEmailToCustomer(whoDidOrder, orderId);
        }
        return order;
    }

    private void sendConfirmingEmailToCustomer(User user, String orderId){
      emailSending.sendEmail(user, orderId, HEADER_FOR_PREPARED_ORDER, MESSAGE_FOR_PREPARED_ORDER);
    }
}
