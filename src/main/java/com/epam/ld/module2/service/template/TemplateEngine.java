package com.epam.ld.module2.service.template;

import com.epam.ld.module2.model.Client;
import com.epam.ld.module2.model.TaggedTemplate;
import com.epam.ld.module2.model.Template;
import com.epam.ld.module2.service.template.exception.NonLatinTemplateException;
import com.epam.ld.module2.service.template.exception.TagsRemainTemplateException;


/**
 * The type Template engine.
 */
public class TemplateEngine {


    /**
     * Generate message string.
     *
     * @param template the text
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        TaggedTemplate taggedTemplate = new TaggedTemplate(template.getText(), client.getTags());
        if(!TemplateValidator.preValidateTemplate(taggedTemplate)){
            throw new NonLatinTemplateException();
       }
        String textAfterReplacement = insertValues(taggedTemplate);
        if (!TemplateValidator.postValidateTemplate(textAfterReplacement)){
            throw new TagsRemainTemplateException();
       }
        return textAfterReplacement;
    }

    private String insertValues(TaggedTemplate taggedTemplate){

        return "";
    }


}
