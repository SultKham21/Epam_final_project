package by.epam.pizzashop.model.verification;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The type Mail sender.
 */
public class MailSender {
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    /**
     * Instantiates a new Mail sender.
     *
     * @param sendToEmail the send to email
     * @param mailSubject the mail subject
     * @param mailText    the mail text
     * @param properties  the properties
     */
    public MailSender(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    /**
     * Send.
     */
//TODO
    public void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {

        } catch (MessagingException e) {

        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}
