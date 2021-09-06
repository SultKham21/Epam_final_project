package com.epam.pizzaplanet.controller.command;

public final class Message {
    public static final String REGISTRATION_ERROR = "Such login already exists";
    public static final String LOGIN_ERROR = "Incorrect login or password";
    public static final String CODE_VERIFICATION_ERROR = "Incorrect verification code";
    public static final String DATA_REGISTRATION_ERROR = "Some fields are incorrect";
    public static final String USER_LOGIN_ERROR = "Login length must be between 1 and 45 symbols";
    public static final String FIRST_NAME_ERROR = "First name must be between 1 and 45 symbols";
    public static final String LAST_NAME_ERROR = "Last name must be between 1 and 45 symbols";
    public static final String INCORRECT_EMAIL = "Incorrect email";
    public static final String INCORRECT_TELEPHONE = "Incorrect telephone";
   
    private Message() {
    }
}
