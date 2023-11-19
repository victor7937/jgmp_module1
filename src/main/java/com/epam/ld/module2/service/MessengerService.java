package com.epam.ld.module2.service;


import com.epam.ld.module2.model.Client;
import com.epam.ld.module2.model.Template;
import com.epam.ld.module2.service.mail.MailServer;
import com.epam.ld.module2.service.template.TemplateEngine;


/**
 * The type Messenger.
 */
public class MessengerService {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     */
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
    public void sendMessage(Client client, Template template) {
        String messageContent =
            templateEngine.generateMessage(template, client);
        mailServer.send(client.getAddresses(), messageContent);
    }
}