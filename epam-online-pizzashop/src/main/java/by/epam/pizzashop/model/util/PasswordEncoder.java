package by.epam.pizzashop.model.util;

import by.epam.pizzashop.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Password encoder.
 */
public class PasswordEncoder {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Create password encoded string.
     *
     * @param password the password
     * @return the string
     * @throws ServiceException the service exception
     */
    public static String createPasswordEncoded(String password) throws ServiceException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.ERROR, "No such algorithm  " + ex.getMessage());
            throw new ServiceException("No such algorithm  " + ex.getMessage());
        }
        byte[] loginHash = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte item : loginHash) {
            builder.append(String.format("%02X", item));
        }
        return builder.toString();
    }
}
