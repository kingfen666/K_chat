package org.kingfen.k_chat.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import java.util.Properties;

@Component
public final class JavaMailUtils {
    MimeMessage message;

    public JavaMailUtils() {
        Properties pros = new Properties();
        pros.put("mail.smtp.host", "smtp.163.com");
        pros.put("mail.smtp.port", "25");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.starttls.enable", "true");

        //创建Session
        Session session = Session.getInstance(pros, new Authenticator() {
            String userName = "a13666861665@163.com";
            String password = "OGYLMYGDPOFBTQGT";

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        message = new MimeMessage(session);
    }

    public void send(String address, String code) throws Exception {
        message.setSubject(code + " 是你的验证码");
        message.setText("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1 style=\"text-align: center\">K_chat</h1>\n" +
                "    <h2 style=\"text-align: center;\">您的验证码是:</h2>\n" +
                "    <h1 style=\"text-align: center;color: #2692e0;\">" + code + "</h1>\n" +
                "</body>\n" +
                "</html>");
        message.setFrom(new InternetAddress("a13666861665@163.com", "K_chat"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(address));
        Transport.send(message);



    }
}
