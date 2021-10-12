package by.epam.pizzashop.entity;

/**
 * The type Basket.
 */
public class Basket {
    private long basketId;
    private User user;
    private Product product;
    private Order order;
    private int quantity;

    /**
     * Instantiates a new Basket.
     */
    public Basket() {
    }

    /**
     * Instantiates a new Basket.
     *
     * @param basketId the basket id
     * @param user     the user
     * @param product  the product
     * @param order    the order
     * @param quantity the quantity
     */
    public Basket(long basketId, User user, Product product, Order order, int quantity) {
        this.basketId = basketId;
        this.user = user;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    /**
     * Gets basket id.
     *
     * @return the basket id
     */
    public long getBasketId() {
        return basketId;
    }

    /**
     * Sets basket id.
     *
     * @param basketId the basket id
     */
    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return basketId == basket.basketId && user == basket.user && product == basket.product
                && order == basket.order && quantity == basket.quantity;
    }

    @Override
    public int hashCode() {
        int result = (int) (basketId ^ (basketId >>> 32));
        result *= 31 + (user != null ? user.hashCode() : 0);
        result *= 31 + (product != null ? product.hashCode() : 0);
        result *= 31 + (order != null ? order.hashCode() : 0);
        result *= quantity;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Basket{");
        builder.append("basketId=").append(basketId);
        builder.append(", user=").append(user);
        builder.append(", product=").append(product);
        builder.append(", order=").append(order);
        builder.append(", quantity=").append(quantity);
        builder.append("}");
        return builder.toString();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Basket newBasket;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            newBasket = new Basket();
        }

        /**
         * Sets basket id.
         *
         * @param basketId the basket id
         * @return the basket id
         */
        public Basket.Builder setBasketId(long basketId) {
            newBasket.basketId = basketId;
            return this;
        }

        /**
         * Sets user.
         *
         * @param user the user
         * @return the user
         */
        public Basket.Builder setUser(User user) {
            newBasket.user = user;
            return this;
        }

        /**
         * Sets product.
         *
         * @param product the product
         * @return the product
         */
        public Basket.Builder setProduct(Product product) {
            newBasket.product = product;
            return this;
        }


        /**
         * Sets order.
         *
         * @param order the order
         * @return the order
         */
        public Basket.Builder setOrder(Order order) {
            newBasket.order = order;
            return this;
        }

        /**
         * Sets quantity.
         *
         * @param quantity the quantity
         * @return the quantity
         */
        public Basket.Builder setQuantity(int quantity) {
            newBasket.quantity = quantity;
            return this;
        }

        /**
         * Build basket.
         *
         * @return the basket
         */
        public Basket build() {
            return newBasket;
        }
    }
}
