package by.epam.pizzashop.model.validation.impl;

import by.epam.pizzashop.controller.command.RequestParameter;
import by.epam.pizzashop.model.validation.RestaurantValidator;

import java.util.Map;

/**
 * The type Restaurant validator.
 */
public class RestaurantValidatorImpl implements RestaurantValidator {

    private static final int MIN_FOR_BLOCK_AND_HOUSE = 0;
    private static final int MAX_SYMBOLS_FOR_CITY_AND_STREET = 70;
    private static final int MAX_SYMBOLS_FOR_HOUSE = 20;
    private static final String EMPTY_STRING = "\s";
    private static final String ZERO_STRING = "0";

    private static RestaurantValidatorImpl instance = new RestaurantValidatorImpl();

    private RestaurantValidatorImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RestaurantValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean isValidForm(Map<String, String> formData) {
        if (!isValidNumber(formData.get(RequestParameter.NUMBER))) {
            formData.put(RequestParameter.NUMBER, EMPTY_STRING);
        }
        if(!isValidCityOrStreet(formData.get(RequestParameter.CITY))) {
            formData.put(RequestParameter.CITY, EMPTY_STRING);
        }
        if (!isValidCityOrStreet(formData.get(RequestParameter.STREET))) {
            formData.put(RequestParameter.STREET, EMPTY_STRING);
        }
        if (!isValidHouse(formData.get(RequestParameter.HOUSE))) {
            formData.put(RequestParameter.HOUSE, EMPTY_STRING);
        }
        if (!isValidBlock(formData)) {
            formData.put(RequestParameter.BLOCK, EMPTY_STRING);
        }
        return !formData.containsValue(EMPTY_STRING);
    }

    @Override
    public boolean isValidNumber(String number) {
        int restaurantNumber;
        try {
            restaurantNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return restaurantNumber > MIN_FOR_BLOCK_AND_HOUSE;
    }

    @Override
    public boolean isValidCityOrStreet(String parameter) {
        return !parameter.isBlank() && parameter.length() <= MAX_SYMBOLS_FOR_CITY_AND_STREET;
    }

    @Override
    public boolean isValidHouse(String house) {
        if(house.equals(ZERO_STRING)) {
            return false;
        }
        return !house.isBlank() && house.length() <= MAX_SYMBOLS_FOR_HOUSE;
    }

    @Override
    public boolean isValidBlock(Map<String, String> formData) {
        String block = formData.get(RequestParameter.BLOCK);
        if (block.isBlank()) {
            formData.put(RequestParameter.BLOCK, ZERO_STRING);
            return true;
        }
        int restaurantBlock;
        try {
           restaurantBlock = Integer.parseInt(block);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return restaurantBlock >= 0;
    }

    @Override
    public boolean isValidNewBlock(String block) {
        int newBlock;
        if(block.isBlank()) {
            return true;
        }
        try {
            newBlock = Integer.parseInt(block);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return newBlock >= MIN_FOR_BLOCK_AND_HOUSE;
    }

}
