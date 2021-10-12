package by.epam.pizzashop.controller.command;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * Default command type.
     */
    DEFAULT,
    /**
     * Login command type.
     */
    LOGIN,
    /**
     * Logout command type.
     */
    LOGOUT,
    /**
     * Registration command type.
     */
    REGISTRATION,
    /**
     * Login page command type.
     */
    LOGIN_PAGE,
    /**
     * Verification customer command type.
     */
    VERIFICATION_CUSTOMER,
    /**
     * Registration page command type.
     */
    REGISTRATION_PAGE,
    /**
     * Verification customer page command type.
     */
    VERIFICATION_CUSTOMER_PAGE,
    /**
     * How to do order page command type.
     */
    HOW_TO_DO_ORDER_PAGE,
    /**
     * About us page command type.
     */
    ABOUT_US_PAGE,
    /**
     * Questions page command type.
     */
    QUESTIONS_PAGE,

    /**
     * All managers command type.
     */
//admin
    ALL_MANAGERS,
    /**
     * Main admin command type.
     */
    MAIN_ADMIN,
    /**
     * Verification manager command type.
     */
    VERIFICATION_MANAGER,
    /**
     * Inactivation manager command type.
     */
    INACTIVATION_MANAGER,
    /**
     * Inactive managers page command type.
     */
    INACTIVE_MANAGERS_PAGE,
    /**
     * Activation manager command type.
     */
    ACTIVATION_MANAGER,
    /**
     * All pharmacies command type.
     */
    ALL_PHARMACIES,
    /**
     * Addition restaurant command type.
     */
    ADDITION_RESTAURANT,
    /**
     * Updating manager login page command type.
     */
    UPDATING_MANAGER_LOGIN_PAGE,
    /**
     * Updating manager first name page command type.
     */
    UPDATING_MANAGER_FIRST_NAME_PAGE,
    /**
     * Updating manager last name page command type.
     */
    UPDATING_MANAGER_LAST_NAME_PAGE,
    /**
     * Updating manager email page command type.
     */
    UPDATING_MANAGER_EMAIL_PAGE,
    /**
     * Updating manager telephone page command type.
     */
    UPDATING_MANAGER_TELEPHONE_PAGE,
    /**
     * Updating manager login command type.
     */
    UPDATING_MANAGER_LOGIN,
    /**
     * Updating manager first name command type.
     */
    UPDATING_MANAGER_FIRST_NAME,
    /**
     * Updating manager last name command type.
     */
    UPDATING_MANAGER_LAST_NAME,
    /**
     * Updating manager email command type.
     */
    UPDATING_MANAGER_EMAIL,
    /**
     * Updating manager telephone command type.
     */
    UPDATING_MANAGER_TELEPHONE,
    /**
     * All products command type.
     */
    ALL_PRODUCTS,
    /**
     * Change language command type.
     */
    CHANGE_LANGUAGE,
    /**
     * Addition product command type.
     */
    ADDITION_PRODUCT,
    /**
     * Addition picture page command type.
     */
    ADDITION_PICTURE_PAGE,
    /**
     * See product command type.
     */
    SEE_PRODUCT,
    /**
     * Updating restaurant number page command type.
     */
    UPDATING_RESTAURANT_NUMBER_PAGE,
    /**
     * Updating restaurant city page command type.
     */
    UPDATING_RESTAURANT_CITY_PAGE,
    /**
     * Updating restaurant street page command type.
     */
    UPDATING_RESTAURANT_STREET_PAGE,
    /**
     * Updating restaurant house page command type.
     */
    UPDATING_RESTAURANT_HOUSE_PAGE,
    /**
     * Updating restaurant block page command type.
     */
    UPDATING_RESTAURANT_BLOCK_PAGE,
    /**
     * Updating restaurant number command type.
     */
    UPDATING_RESTAURANT_NUMBER,
    /**
     * Updating restaurant city command type.
     */
    UPDATING_RESTAURANT_CITY,
    /**
     * Updating restaurant street command type.
     */
    UPDATING_RESTAURANT_STREET,
    /**
     * Updating restaurant house command type.
     */
    UPDATING_RESTAURANT_HOUSE,
    /**
     * Updating restaurant block command type.
     */
    UPDATING_RESTAURANT_BLOCK,
    /**
     * Updating product name page command type.
     */
    UPDATING_PRODUCT_NAME_PAGE,
    /**
     * Updating product non proprietary name page command type.
     */
    UPDATING_PRODUCT_NON_PROPRIETARY_NAME_PAGE,
    /**
     * Updating product dose page command type.
     */
    UPDATING_PRODUCT_DOSE_PAGE,
    /**
     * Updating product group page command type.
     */
    UPDATING_PRODUCT_GROUP_PAGE,
    /**
     * Updating product price page command type.
     */
    UPDATING_PRODUCT_PRICE_PAGE,
    /**
     * Updating product instruction page command type.
     */
    UPDATING_PRODUCT_INSTRUCTION_PAGE,
    /**
     * Updating product name command type.
     */
    UPDATING_PRODUCT_NAME,
    /**
     * Updating product non proprietary name command type.
     */
    UPDATING_PRODUCT_NON_PROPRIETARY_NAME,
    /**
     * Updating product dose command type.
     */
    UPDATING_PRODUCT_DOSE,
    /**
     * Updating product group command type.
     */
    UPDATING_PRODUCT_GROUP,
    /**
     * Updating product price command type.
     */
    UPDATING_PRODUCT_PRICE,
    /**
     * Updating product instruction command type.
     */
    UPDATING_PRODUCT_INSTRUCTION,


    /**
     * Main manager command type.
     */
//manager
    MAIN_MANAGER,
    /**
     * All processing orders command type.
     */
    ALL_PROCESSING_ORDERS,
    /**
     * Basket for order command type.
     */
    BASKET_FOR_ORDER,
    /**
     * Updating order status to prepared command type.
     */
    UPDATING_ORDER_STATUS_TO_PREPARED,
    /**
     * Updating order status to deleted command type.
     */
    UPDATING_ORDER_STATUS_TO_DELETED,

    /**
     * Main customer command type.
     */
//customer
    MAIN_CUSTOMER,
    /**
     * Search products by name page command type.
     */
    SEARCH_PRODUCTS_BY_NAME_PAGE,
    /**
     * Search products by non proprietary name page command type.
     */
    SEARCH_PRODUCTS_BY_NON_PROPRIETARY_NAME_PAGE,
    /**
     * Search products by name command type.
     */
    SEARCH_PRODUCTS_BY_NAME,
    /**
     * Search products by non proprietary name command type.
     */
    SEARCH_PRODUCTS_BY_NON_PROPRIETARY_NAME,
    /**
     * Search pharmacies by city command type.
     */
    SEARCH_PHARMACIES_BY_CITY,
    /**
     * About product command type.
     */
    ABOUT_PRODUCT,
    /**
     * Addition product to order command type.
     */
    ADDITION_PRODUCT_TO_ORDER,
    /**
     * Basket page command type.
     */
    BASKET_PAGE,
    /**
     * Updating product quantity command type.
     */
    UPDATING_PRODUCT_QUANTITY,
    /**
     * Choose restaurant command type.
     */
    CHOOSE_RESTAURANT,
    /**
     * Order command type.
     */
    ORDER,
    /**
     * Send order command type.
     */
    SEND_ORDER

}
