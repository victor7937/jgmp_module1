package com.epam.ld.module2.controller.handler;

import com.epam.ld.module2.model.dto.ResponseExceptionMessage;
import com.epam.ld.module2.service.mail.exception.EmailNotSentException;
import com.epam.ld.module2.service.template.exception.NonLatinTemplateException;
import com.epam.ld.module2.service.template.exception.TagsRemainTemplateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ResponseExceptionMessage> handleException(ResponseStatusException e){
        ResponseExceptionMessage message = new ResponseExceptionMessage(e.getStatusCode(), e.getReason());
        return new ResponseEntity<>(message, message.getStatus());
    }

    @ExceptionHandler(value = TagsRemainTemplateException.class)
    public ResponseEntity<ResponseExceptionMessage> handleException(TagsRemainTemplateException e){
        ResponseExceptionMessage message = new ResponseExceptionMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(message, message.getStatus());
    }

    @ExceptionHandler(value = NonLatinTemplateException.class)
    public ResponseEntity<ResponseExceptionMessage> handleException(NonLatinTemplateException e){
        ResponseExceptionMessage message = new ResponseExceptionMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(message, message.getStatus());
    }

    @ExceptionHandler(value = EmailNotSentException.class)
    public ResponseEntity<ResponseExceptionMessage> handleException(EmailNotSentException e){
        ResponseExceptionMessage message = new ResponseExceptionMessage(HttpStatus.BAD_GATEWAY, e.getMessage());
        return new ResponseEntity<>(message, message.getStatus());
    }





}
