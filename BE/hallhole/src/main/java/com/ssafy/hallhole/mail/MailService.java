package com.ssafy.hallhole.mail;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailService {

    private final Environment env;

    public void sendEmail(){
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("wodnd101@gmail.com", env.getProperty("mail.password"));
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("wodnd101@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("wodnd101@naver.com"));
            message.setSubject("your subject");
            message.setText("The body text here");
            Transport.send(message);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
