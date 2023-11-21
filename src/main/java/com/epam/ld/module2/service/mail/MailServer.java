package com.epam.ld.module2.service.mail;

import com.epam.ld.module2.service.mail.exception.EmailNotSentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Mail server class.
 */
@Component
@Slf4j
public class MailServer {

    public static final String HOST_NAME = "smtp.yandex.ru";
    public static final int SMTP_PORT = 465;
    public MailServer() {
    }


    public String send(String to, String from, String password, String text, String subject) {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(HOST_NAME);
            email.setSmtpPort(SMTP_PORT);
            email.setCharset("UTF-8");
            email.setSSLOnConnect(true);
            email.addTo(to);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(text);
            email.setAuthentication(from, password);
            return email.send();
        } catch (EmailException e) {
            log.error("Email error", e);
            throw new EmailNotSentException("MailServer error while sending email", e);
        }
    }
}
