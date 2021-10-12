package by.epam.pizzashop.model.validation.impl;

import by.epam.pizzashop.model.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUserValidatorImpl {
    UserValidator userValidator;
    String correctParameter;
    String emptyParameter;
    String longParameter;
    String correctEmail;
    String incorrectEmail;
    String correctTelephone;
    String incorrectTelephone;
    Map<String, String> data;

    @BeforeEach
    public void setUp() {
        userValidator = UserValidatorImpl.getInstance();
        correctParameter = "parameter";
        emptyParameter = "\s";
        correctEmail = "email@mail.ru";
        incorrectEmail = "email@";
        correctTelephone = "+375291112233";
        incorrectTelephone = "+37529111223";
        data = new LinkedHashMap<>();
        data.put("login", "login");
        data.put("password", "password");
        data.put("firstName", "firsName");
        data.put("lastName", "lastName");
        data.put("email", correctEmail);
        data.put("telephone", correctTelephone);
        longParameter = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                qui officia deserunt mollit anim id est laborum.
                """;
    }

    @Test
    public void testIsValidForm() {
       boolean result = userValidator.isValidForm(data);
       assertTrue(result);
    }

    @Test
    public void testIsValidFormFalseEmptyLogin() {
        data.put("login", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLogin() {
        data.put("login", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseEmptyPassword() {
        data.put("password", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseEmptyFirstName() {
        data.put("firstName", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseEmptyLastName() {
        data.put("lastName", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }


    @Test
    public void testIsValidStringParameter() {
        boolean result = userValidator.isValidStringParameter(correctParameter);
        assertTrue(result);
    }

    @Test
    public void testIsValidFormFalseEmptyEmail() {
        data.put("email", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseEmptyTelephone() {
        data.put("telephone", emptyParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLongLogin() {
        data.put("login", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLongPassword() {
        data.put("password", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLongFirstName() {
        data.put("firstName", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLongLastName() {
        data.put("lastName", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLongEmail() {
        data.put("email", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLongTelephone() {
        data.put("telephone", longParameter);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseIncorrectEmail() {
        data.put("email", incorrectEmail);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseIncorrectTelephone() {
        data.put("telephone", incorrectTelephone);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseLongEmailIncorrectTelephone() {
        data.put("email", longParameter);
        data.put("telephone", incorrectTelephone);
        boolean result = userValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidStringParameterFalseParameterIsEmpty() {
        boolean result = userValidator.isValidStringParameter(emptyParameter);
        assertFalse(result);
    }

    @Test
    public void testIsValidStringParameterFalseParameterIsLong() {
        boolean result = userValidator.isValidStringParameter(longParameter);
        assertFalse(result);
    }

    @Test
    public void testIsValidEmailRegistrationUser() {
        boolean result = userValidator.isValidEmailRegistrationUser(correctEmail);
        assertTrue(result);
    }

    @Test
    public void testIsValidEmailRegistrationUserFalseParameterIsEmpty() {
        boolean result = userValidator.isValidEmailRegistrationUser(emptyParameter);
        assertFalse(result);
    }

    @Test
    public void testIsValidEmailRegistrationUserFalseParameterIsLong() {
        boolean result = userValidator.isValidEmailRegistrationUser(longParameter);
        assertFalse(result);
    }

    @Test
    public void testIsValidEmailRegistrationUserParameterFalseIsIncorrect() {
        boolean result = userValidator.isValidEmailRegistrationUser(incorrectEmail);
        assertFalse(result);
    }

    @Test
    public void testIsValidTelephoneRegistrationUser() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(correctTelephone);
        assertTrue(result);
    }

    @Test
    public void testIsValidTelephoneRegistrationUserFalseParameterIsEmpty() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(emptyParameter);
        assertFalse(result);
    }

    @Test
    public void testIsValidTelephoneRegistrationUserFalseParameterIsLong() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(longParameter);
        assertFalse(result);
    }

    @Test
    public void testIsValidTelephoneRegistrationUserFalseParameterIsIncorrect() {
        boolean result = userValidator.isValidTelephoneRegistrationUser(incorrectTelephone);
        assertFalse(result);
    }
}
