package by.epam.pizzashop.model.dao;

/**
 * The type Column name.
 */
public final class ColumnName {
    /**
     * The constant USER_ID.
     */
//table users
    public static final String USER_ID = "user_id";
    /**
     * The constant USER_LOGIN.
     */
    public static final String USER_LOGIN = "login";
    /**
     * The constant USER_PASSWORD.
     */
    public static final String USER_PASSWORD = "password";
    /**
     * The constant USER_FIRST_NAME.
     */
    public static final String USER_FIRST_NAME = "first_name";
    /**
     * The constant USER_LAST_NAME.
     */
    public static final String USER_LAST_NAME = "last_name";
    /**
     * The constant USER_EMAIL.
     */
    public static final String USER_EMAIL = "email";
    /**
     * The constant USER_TELEPHONE.
     */
    public static final String USER_TELEPHONE = "telephone";
    /**
     * The constant USER_STATUS_ID.
     */
    public static final String USER_STATUS_ID = "status_id";
    /**
     * The constant USER_ROLE_ID.
     */
    public static final String USER_ROLE_ID = "role_id";

    /**
     * The constant USER_STATUS.
     */
//table user_status
    public static final String USER_STATUS = "status";

    /**
     * The constant USER_ROLE.
     */
//table user_role
    public static final String USER_ROLE = "role";

    /**
     * The constant RESTAURANT_ID.
     */
//table pharmacies
    public static final String RESTAURANT_ID = "restaurant_id";
    /**
     * The constant RESTAURANT_NUMBER.
     */
    public static final String RESTAURANT_NUMBER = "number";
    /**
     * The constant RESTAURANT_CITY.
     */
    public static final String RESTAURANT_CITY = "city";
    /**
     * The constant RESTAURANT_STREET.
     */
    public static final String RESTAURANT_STREET = "street";
    /**
     * The constant RESTAURANT_HOUSE.
     */
    public static final String RESTAURANT_HOUSE = "house";
    /**
     * The constant RESTAURANT_BLOCK.
     */
    public static final String RESTAURANT_BLOCK = "block";

    /**
     * The constant PRODUCT_ID.
     */
//table products
    public static final String PRODUCT_ID = "product_id";
    /**
     * The constant PRODUCT_NAME.
     */
    public static final String PRODUCT_NAME = "product_name";
    /**
     * The constant PRODUCT_DOSE.
     */
    public static final String PRODUCT_DOSE = "product_dose";
    /**
     * The constant PRODUCT_GROUP.
     */
    public static final String PRODUCT_GROUP = "product_group";
    /**
     * The constant PRODUCT_PRICE.
     */
    public static final String PRODUCT_PRICE = "price";
    /**
     * The constant PRODUCT_PICTURE.
     */
    public static final String PRODUCT_PICTURE = "picture";
    /**
     * The constant PRODUCT_INSTRUCTION.
     */
    public static final String PRODUCT_INSTRUCTION = "instruction";

    /**
     * The constant ORDER_ID.
     */
//table orders
    public static final String ORDER_ID = "order_id";
    /**
     * The constant DATA_STARTING.
     */
    public static final String DATA_STARTING = "data_starting";
    /**
     * The constant DATA_ENDING.
     */
    public static final String DATA_ENDING = "data_ending";

    /**
     * The constant ORDER_STATUS.
     */
//table order_status
    public static final String ORDER_STATUS = "status";

    /**
     * The constant QUANTITY.
     */
//table basket
    public static final String QUANTITY = "quantity";

    private ColumnName() {
    }
}
