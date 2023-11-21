package com.epam.ld.module2.service.template;

import com.epam.ld.module2.model.Client;
import com.epam.ld.module2.model.TaggedTemplate;
import com.epam.ld.module2.model.Template;
import com.epam.ld.module2.service.template.exception.NonLatinTemplateException;
import com.epam.ld.module2.service.template.exception.TagsRemainTemplateException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.ld.module2.service.template.Constant.TAG_REGEX;


/**
 * The type Template engine.
 */
@Component
public class TemplateEngine {


    public TemplateEngine() {
    }

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
        Map<String, String> tags = taggedTemplate.getTags();
        String text = taggedTemplate.getText();
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile(TAG_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String whatWasFound = matcher.group(1);
            String tagValue = tags.get(whatWasFound);
            if (tagValue != null) {
                matcher.appendReplacement(sb, tagValue);
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }


}
