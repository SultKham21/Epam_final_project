package by.epam.pizzashop.entity;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * The type Order.
 */
public class Order {
    private long orderId;
    private Timestamp dataStarting;
    private Timestamp dataEnding;
    private StatusOrder statusOrder;
    private Restaurant restaurant;

    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param orderId      the order id
     * @param dataStarting the data starting
     * @param dataEnding   the data ending
     * @param statusOrder  the status order
     * @param restaurant   the restaurant
     */
    public Order(long orderId, Timestamp dataStarting, Timestamp dataEnding, StatusOrder statusOrder, Restaurant restaurant) {
        this.orderId = orderId;
        this.dataStarting = dataStarting;
        this.dataEnding = dataEnding;
        this.statusOrder = statusOrder;
        this.restaurant = restaurant;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets data starting.
     *
     * @return the data starting
     */
    public Timestamp getDataStarting() {
        return dataStarting;
    }

    /**
     * Sets data starting.
     *
     * @param dataStarting the data starting
     */
    public void setDataStarting(Timestamp dataStarting) {
        this.dataStarting = dataStarting;
    }

    /**
     * Gets data ending.
     *
     * @return the data ending
     */
    public Timestamp getDataEnding() {
        return dataEnding;
    }

    /**
     * Sets data ending.
     *
     * @param dataEnding the data ending
     */
    public void setDataEnding(Timestamp dataEnding) {
        this.dataEnding = dataEnding;
    }

    /**
     * Gets status order.
     *
     * @return the status order
     */
    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    /**
     * Sets status order.
     *
     * @param statusOrder the status order
     */
    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    /**
     * Gets restaurant.
     *
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets pharmacy.
     *
     * @param restaurant the restaurant
     */
    public void setPharmacy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && restaurant == order.restaurant && Objects.equals(dataStarting, order.dataStarting)
                && Objects.equals(dataEnding, order.dataEnding) && statusOrder == order.statusOrder;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result *= 31 + (dataStarting != null ? dataStarting.hashCode() : 0);
        result *= 31 + (dataEnding != null ? dataEnding.hashCode() : 0);
        result *= 31 + (statusOrder != null ? statusOrder.hashCode() : 0);
        result *= 31 + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order{");
        builder.append("orderId=").append(orderId);
        builder.append(", dataStarting=").append(dataStarting);
        builder.append(", dataEnding=").append(dataEnding);
        builder.append(", statusOrder=").append(statusOrder);
        builder.append(", restaurant=").append(restaurant);
        builder.append("}");
        return builder.toString();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Order newOrder;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            newOrder = new Order();
        }

        /**
         * Sets order id.
         *
         * @param orderId the order id
         * @return the order id
         */
        public Order.Builder setOrderId(long orderId) {
            newOrder.orderId = orderId;
            return this;
        }

        /**
         * Sets data starting.
         *
         * @param dataStarting the data starting
         * @return the data starting
         */
        public Order.Builder setDataStarting(Timestamp dataStarting) {
            newOrder.dataStarting = dataStarting;
            return this;
        }

        /**
         * Sets data ending.
         *
         * @param dataEnding the data ending
         * @return the data ending
         */
        public Order.Builder setDataEnding(Timestamp dataEnding) {
            newOrder.dataEnding = dataEnding;
            return this;
        }


        /**
         * Sets status order.
         *
         * @param statusOrder the status order
         * @return the status order
         */
        public Order.Builder setStatusOrder(StatusOrder statusOrder) {
            newOrder.statusOrder = statusOrder;
            return this;
        }

        /**
         * Sets restaurant.
         *
         * @param restaurant the restaurant
         * @return the restaurant
         */
        public Order.Builder setRestaurant(Restaurant restaurant) {
            newOrder.restaurant = restaurant;
            return this;
        }

        /**
         * Build order.
         *
         * @return the order
         */
        public Order build() {
            return newOrder;
        }
    }
}
