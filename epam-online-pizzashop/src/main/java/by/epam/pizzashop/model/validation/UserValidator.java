package by.epam.pizzashop.model.validation;

import java.util.Map;

/**
 * The interface User validator.
 */
public interface UserValidator {
    /**
     * Is valid form boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean isValidForm(Map<String,String> formData);

    /**
     * Is valid string parameter boolean.
     *
     * @param parameter the parameter
     * @return the boolean
     */
    boolean isValidStringParameter(String parameter);

    /**
     * Is valid email registration user boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isValidEmailRegistrationUser(String email);

    /**
     * Is valid telephone registration user boolean.
     *
     * @param telephone the telephone
     * @return the boolean
     */
    boolean isValidTelephoneRegistrationUser(String telephone);
}
