package com.epam.pizzaplanet.verification;



import com.epam.pizzaplanet.entity.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class EmailSending {
    public static void sendEmail(User user, String code) {

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties"));
            //TODO
            properties.load(new FileReader("E://epam//onlinepharmacy//src//main//resources//mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (!user.getEmail().isEmpty()) {
            String message = String.format(
                    "Hello, %s  %s welcome to Alpha Pharmacy. Your activation code is %s",
                    user.getFirstName(),
                    user.getLastName(),
                    code
            );

            String header = "Activation from Alpha Pharmacy";
            MailSender sender = new MailSender(user.getEmail(), header, message, properties);
            sender.send();
        }
    }
}
