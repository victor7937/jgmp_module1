package com.epam.ld.module2.service;


import com.epam.ld.module2.model.Client;
import com.epam.ld.module2.model.Message;
import com.epam.ld.module2.model.Template;
import com.epam.ld.module2.service.mail.MailServer;
import com.epam.ld.module2.service.template.TemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The type Messenger.
 */
@Service
@Slf4j
public class MessengerService {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     */
    @Autowired
    public MessengerService(MailServer mailServer,
                            TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }


    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     */
    public Message sendMessage(Client client, Template template) {
        String messageContent = templateEngine.generateMessage(template, client);
        String messageId = mailServer.send(
                client.getTargetEmail(),
                client.getEmail(),
                client.getPassword(),
                messageContent,
                client.getSubject());
        log.info("New message " + messageId + " was sent");
        return Message.builder()
                .id(messageId)
                .content(messageContent)
                .build();
    }
}