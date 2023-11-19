package com.epam.ld.module2.model;

import java.util.Map;

public class TaggedTemplate {

    private String text;

    private Map<String, String> tags;

    public TaggedTemplate() {
    }

    public TaggedTemplate(String text, Map<String, String> tags) {
        this.text = text;
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
