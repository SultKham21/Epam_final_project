package by.epam.pizzashop.model.dao.impl;

import by.epam.pizzashop.entity.Basket;
import by.epam.pizzashop.entity.Order;
import by.epam.pizzashop.entity.Restaurant;
import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.entity.StatusOrder;
import by.epam.pizzashop.entity.User;
import by.epam.pizzashop.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestOrderDaoImpl {

    @Mock
    private OrderDaoImpl orderDao;
    private Order order;
    private Order testOrder;
    private Basket basket;
    private Restaurant Restaurant;
    private List<Order> orders;
    private List<Basket> basketList;

    @BeforeEach
    public void setUp() {
        Restaurant = new Restaurant.Builder()
                .setRestaurantId(1)
                .build();
        order = new Order.Builder()
                .setOrderId(1)
                .setStatusOrder(StatusOrder.PROCESSING)
                .setDataStarting(Timestamp.valueOf(LocalDateTime.now()))
                .setDataEnding(Timestamp.valueOf(LocalDateTime.now().plusDays(1)))
                .setRestaurant(Restaurant)
                .build();
        testOrder = new Order.Builder()
                .setStatusOrder(StatusOrder.PROCESSING)
                .setRestaurant(Restaurant)
                .build();
        basket = new Basket.Builder()
                .setBasketId(1)
                .setProduct(new Product())
                .setUser(new User())
                .setOrder(new Order())
                .setQuantity(1)
                .build();
        orders = new ArrayList<>();
        orders.add(order);
        orders.add(testOrder);
        basketList = new ArrayList<>();
        basketList.add(basket);
    }

    @Test
    public void createOrderTest() throws DaoException {
        when(orderDao.createOrder(order)).thenReturn(order);
        Order actualOrder = orderDao.createOrder(order);
        assertEquals(order, actualOrder);
    }

    @Test
    public void createOrderFalseTest() throws DaoException {
        when(orderDao.createOrder(order)).thenReturn(order);
        Order actualOrder = orderDao.createOrder(order);
        assertNotEquals(testOrder, actualOrder);
    }

    @Test
    public void findOrderByIdTest() throws DaoException {
        when(orderDao.findOrderById(1)).thenReturn(Optional.of(order));
        Optional<Order> actualOrder = orderDao.findOrderById(1);
        assertEquals(order, actualOrder.get());
    }

}
