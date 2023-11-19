package com.epam.ld.module2.service.template.exception;

public class NonLatinTemplateException extends TemplateException{
    public NonLatinTemplateException() {
        super("Template and variables should be Latin-1!");
    }

    public NonLatinTemplateException(String message) {
        super(message);
    }
}
