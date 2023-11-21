package com.epam.ld.module2.service.template.exception;

public class TagsRemainTemplateException extends TemplateException{
    public TagsRemainTemplateException() {
        super("There are a some tags that are still unpopulated!");
    }

    public TagsRemainTemplateException(String message) {
        super(message);
    }
}
