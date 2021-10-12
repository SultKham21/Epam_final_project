package by.epam.pizzashop.model.verification;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * The type Session factory.
 */
public class SessionFactory {
    /**
     * Create session session.
     *
     * @param properties the properties
     * @return the session
     */
    public static Session createSession(Properties properties) {
        String userName = properties.getProperty("mail.user.name");
        String userPassword = properties.getProperty("mail.user.password");
        return Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
