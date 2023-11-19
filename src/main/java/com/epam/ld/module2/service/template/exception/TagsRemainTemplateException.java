package com.epam.ld.module2.service.template.exception;

public class TagsRemainTemplateException extends TemplateException{
    public TagsRemainTemplateException() {
        super("Several tags are still remain!");
    }

    public TagsRemainTemplateException(String message) {
        super(message);
    }
}
