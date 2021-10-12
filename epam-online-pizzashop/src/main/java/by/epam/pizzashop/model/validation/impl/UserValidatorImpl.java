package by.epam.pizzashop.model.validation.impl;

import by.epam.pizzashop.controller.command.RequestParameter;
import by.epam.pizzashop.model.validation.UserValidator;

import java.util.Map;

/**
 * The type User validator.
 */
public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_VALIDATOR = "^[\\w.!#$%&’*+/=?^_`{|}~-]+@\\w+\\.\\w+$"; //TODO
    private static final String TELEPHONE_VALIDATOR = "^\\+[0-9]{12}$";
    private static final int MAX_SYMBOLS_DATA_USER = 45;
    private static final String BLANK_STRING = "\s";

    private static UserValidatorImpl instance = new UserValidatorImpl();

    private UserValidatorImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean isValidForm(Map<String, String> formData) {
        if (!isValidStringParameter(formData.get(RequestParameter.LOGIN))) {
            formData.put(RequestParameter.LOGIN, BLANK_STRING);
        }
        if (!isValidStringParameter(formData.get(RequestParameter.PASSWORD))) {
            formData.put(RequestParameter.PASSWORD, BLANK_STRING);
        }
        if (!isValidStringParameter(formData.get(RequestParameter.FIRST_NAME))) {
            formData.put(RequestParameter.FIRST_NAME, BLANK_STRING);
        }
        if (!isValidStringParameter(formData.get(RequestParameter.LAST_NAME))) {
            formData.put(RequestParameter.LAST_NAME, BLANK_STRING);
        }
        if (!isValidEmailRegistrationUser(formData.get(RequestParameter.EMAIL))) {
            formData.put(RequestParameter.EMAIL, BLANK_STRING);
        }
        if (!isValidTelephoneRegistrationUser(formData.get(RequestParameter.TELEPHONE))) {
            formData.put(RequestParameter.TELEPHONE, BLANK_STRING);
        }
        return !formData.containsValue(BLANK_STRING);
    }

    @Override
    public boolean isValidStringParameter(String parameter) {
        return !parameter.isBlank() && parameter.length() <= MAX_SYMBOLS_DATA_USER;
    }

    @Override
    public boolean isValidEmailRegistrationUser(String email) {
        return !email.isBlank() && email.length() <= MAX_SYMBOLS_DATA_USER && email.matches(EMAIL_VALIDATOR);
    }
    
    @Override
    public boolean isValidTelephoneRegistrationUser(String telephone) {
        return !telephone.isBlank() && telephone.length() <= MAX_SYMBOLS_DATA_USER && telephone.matches(TELEPHONE_VALIDATOR);
    }
}
