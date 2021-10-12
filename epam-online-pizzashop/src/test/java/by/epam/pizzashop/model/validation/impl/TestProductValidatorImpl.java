package by.epam.pizzashop.model.validation.impl;

import by.epam.pizzashop.model.validation.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProductValidatorImpl {
    ProductValidator productValidator;
    String correctParameter;
    String emptyString;
    String longString;
    String correctPrice;
    String zero;
    String negativeNumber;
    Map<String, String> data;

    @BeforeEach
    public void setUp() {
        productValidator = ProductValidatorImpl.getInstance();
        correctParameter = "parameter";
        emptyString = "\s";
        correctPrice = "15.30";
        zero = "0";
        negativeNumber = "-5.00";
        data = new LinkedHashMap<>();
        data.put("name", "name");
        data.put("dose", "dose");
        data.put("group", "group");
        data.put("price", "15.98");
        data.put("instruction", "instruction");
        longString = """
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
       boolean result = productValidator.isValidForm(data);
       assertTrue(result);
    }

    @Test
    public void testIsValidFormFalseNameEmpty() {
        data.put("name", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseNameLong() {
        data.put("name", longString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseDoseEmpty() {
        data.put("dose", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseDoseLong() {
        data.put("dose", longString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseGroupEmpty() {
        data.put("group", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseGroupLong() {
        data.put("group", longString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }



    @Test
    public void testIsValidFormFalsePriceEmpty() {
        data.put("price", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalsePriceIncorrect() {
        data.put("price", negativeNumber);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidFormFalseInstructionEmpty() {
        data.put("instruction", emptyString);
        boolean result = productValidator.isValidForm(data);
        assertFalse(result);
    }

    @Test
    public void testIsValidStringParameter() {
        boolean result = productValidator.isValidStringParameters(correctParameter);
        assertTrue(result);
    }

    @Test
    public void testIsValidStringParameterFalseEmptyParameter() {
        boolean result = productValidator.isValidStringParameters(emptyString);
        assertFalse(result);
    }

    @Test
    public void testIsValidStringParameterFalseLongParameter() {
        boolean result = productValidator.isValidStringParameters(longString);
        assertFalse(result);
    }


    @Test
    public void testIsValidPrice() {
        boolean result = productValidator.isValidPrice(correctPrice);
        assertTrue(result);
    }

    @Test
    public void testIsValidPriceParameterZero() {
        boolean result = productValidator.isValidPrice(zero);
        assertTrue(result);
    }

    @Test
    public void testIsValidPriceFalseTextParameter() {
        boolean result = productValidator.isValidPrice(correctParameter);
        assertFalse(result);
    }

    @Test
    public void testIsValidPriceFalseNegativeNumber() {
        boolean result = productValidator.isValidPrice(negativeNumber);
        assertFalse(result);
    }

    @Test
    public void testIsValidPriceFalseEmptyString() {
        boolean result = productValidator.isValidPrice(emptyString);
        assertFalse(result);
    }

    @Test
    public void testIsValidInstruction() {
        boolean result = productValidator.isValidInstruction(longString);
        assertTrue(result);
    }

    @Test
    public void testIsValidInstructionFalse() {
        boolean result = productValidator.isValidInstruction(emptyString);
        assertFalse(result);
    }
}
