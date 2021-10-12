package by.epam.pizzashop.model.validation;

import java.util.Map;

/**
 * The interface Product validator.
 */
public interface ProductValidator {
    /**
     * Is valid form boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean isValidForm(Map<String, String> formData);

    /**
     * Is valid string parameters boolean.
     *
     * @param parameter the parameter
     * @return the boolean
     */
    boolean isValidStringParameters(String parameter);

    /**
     * Is valid price boolean.
     *
     * @param price the price
     * @return the boolean
     */
    boolean isValidPrice(String price);

    /**
     * Is valid instruction boolean.
     *
     * @param instruction the instruction
     * @return the boolean
     */
    boolean isValidInstruction(String instruction);
}
