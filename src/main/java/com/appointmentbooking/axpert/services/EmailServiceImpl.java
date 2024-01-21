package com.appointmentbooking.axpert.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    public static boolean sendForgotPasswordEmail(String recipientEmail, String htmlString) {
        /*
         * final String username = environment.getProperty("spring.mail.username");
         * final String password = environment.getProperty("spring.mail.password");
         */
        final String username = "area.xperties@gmail.com";
        final String password = "haqp vuau fsxs vepg";
        // Email configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(username));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            // Set Subject: header field
            message.setSubject("Forgot Password");
            // Set Content: header field
            String emailContent = htmlString;
            message.setContent(emailContent, "text/html");

            // Send the message
            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean handleForgotPasswordRequest(String userEmail, String htmlstring) {
        return sendForgotPasswordEmail(userEmail, htmlstring);

    }

}
