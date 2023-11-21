package com.epam.ld.module2.controller;

import com.epam.ld.module2.model.Client;
import com.epam.ld.module2.model.Template;
import com.epam.ld.module2.model.dto.SendMailRequestDto;
import com.epam.ld.module2.model.dto.SendMailResponseDto;
import com.epam.ld.module2.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MessageController {

    private final MessengerService messengerService;

    @Autowired
    public MessageController(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    @PostMapping("/send")
    public SendMailResponseDto sendMail(@RequestBody SendMailRequestDto requestDto){
        Client client = mapToClient(requestDto);
        Template template = new Template(requestDto.getText());
        String messageId = messengerService.sendMessage(client, template);
        String messageContent = messengerService.getContent(messageId);
        return new SendMailResponseDto(messageId, messageContent, client.getEmail(), client.getTargetEmail());
    }

    private Client mapToClient(SendMailRequestDto requestDto){
        return Client.builder()
                .email(requestDto.getFrom())
                .password(requestDto.getPassword())
                .targetEmail(requestDto.getTo())
                .subject(requestDto.getSubject())
                .tags(requestDto.getTags())
                .build();
    }



}
