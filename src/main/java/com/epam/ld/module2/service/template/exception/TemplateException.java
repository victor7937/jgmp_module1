package com.epam.ld.module2.service.template.exception;

public abstract class TemplateException extends RuntimeException{
    public TemplateException(String message) {
        super(message);
    }
}
