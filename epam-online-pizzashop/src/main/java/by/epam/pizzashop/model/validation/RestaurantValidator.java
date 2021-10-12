package by.epam.pizzashop.model.validation;

import java.util.Map;

/**
 * The interface Restaurant validator.
 */
public interface RestaurantValidator {
    /**
     * Is valid form boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean isValidForm(Map<String, String> formData);

    /**
     * Is valid number boolean.
     *
     * @param number the number
     * @return the boolean
     */
    boolean isValidNumber(String number);

    /**
     * Is valid city or street boolean.
     *
     * @param parameter the parameter
     * @return the boolean
     */
    boolean isValidCityOrStreet(String parameter);

    /**
     * Is valid house boolean.
     *
     * @param house the house
     * @return the boolean
     */
    boolean isValidHouse(String house);

    /**
     * Is valid block boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean isValidBlock(Map<String, String> formData);

    /**
     * Is valid new block boolean.
     *
     * @param block the block
     * @return the boolean
     */
    boolean isValidNewBlock(String block);
}
