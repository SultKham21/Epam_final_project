package com.epam.pizzaplanet.util;


import com.epam.pizzaplanet.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static final Logger logger = LogManager.getLogger();

    public static String createPasswordEncoded(String password) throws ServiceException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, "No such algorithm  " , e);
            throw new ServiceException("No such algorithm  " , e);
        }
        byte[] loginHash = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte item : loginHash) {
            builder.append(String.format("%02X", item));
        }
        return builder.toString();
    }
}
