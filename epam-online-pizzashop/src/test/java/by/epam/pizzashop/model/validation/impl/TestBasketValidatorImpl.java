package by.epam.pizzashop.model.validation.impl;

import by.epam.pizzashop.model.validation.BasketValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBasketValidatorImpl {
    BasketValidator basketValidator;
    String quantity;
    String negativeQuantity;
    String fractionalQuantity;
    String zero;
    String word;

    @BeforeEach
    public void setUp() {
        basketValidator = BasketValidatorImpl.getInstance();
        quantity = "5";
        negativeQuantity = "-5";
        fractionalQuantity = "0.0";
        zero = "0";
        word = "parameter";
    }

    @Test
    public void testIsValidProductQuantity() {
        boolean result = basketValidator.isValidProductQuantity(quantity);
        assertTrue(result);
    }

    @Test
    public void testIsValidProductQuantityZero() {
        boolean result = basketValidator.isValidProductQuantity(zero);
        assertTrue(result);
    }

    @Test
    public void testIsValidProductQuantityFalseNegativeQuantity() {
        boolean result = basketValidator.isValidProductQuantity(negativeQuantity);
        assertFalse(result);
    }

    @Test
    public void testIsValidProductQuantityFalseFractionalQuantity() {
        boolean result = basketValidator.isValidProductQuantity(fractionalQuantity);
        assertFalse(result);
    }

    @Test
    public void testIsValidProductQuantityFalseWord() {
        boolean result = basketValidator.isValidProductQuantity(word);
        assertFalse(result);
    }
}
