package by.epam.pizzashop.model.validation.impl;

import by.epam.pizzashop.model.validation.BasketValidator;

/**
 * The type Basket validator.
 */
public class BasketValidatorImpl implements BasketValidator {
    private static final int MIN_QUANTITY = 0;
    private static BasketValidatorImpl instance = new BasketValidatorImpl();

    private BasketValidatorImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static BasketValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean isValidProductQuantity(String quantity) {
        int productQuantity;
        try {
          productQuantity = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            return false;
        }
        return productQuantity >= MIN_QUANTITY;
    }

}
