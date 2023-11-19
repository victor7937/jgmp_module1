package com.epam.ld.module2.service.template;

import com.epam.ld.module2.model.TaggedTemplate;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.ld.module2.service.template.Constant.LATIN1;
import static com.epam.ld.module2.service.template.Constant.TAG_REGEX;


public class TemplateValidator {
    public static boolean preValidateTemplate(TaggedTemplate taggedTemplate){
        return taggedTemplate.getText().matches(LATIN1) && allValuesMatchesLatin(taggedTemplate.getTags());
    }

    public static boolean postValidateTemplate(String textAfterTagging){
        Pattern pattern = Pattern.compile(TAG_REGEX);
        Matcher matcher = pattern.matcher(textAfterTagging);
        return !matcher.find();
    }

    private static boolean allValuesMatchesLatin(Map<String, String> tags){
        return tags.values().stream().allMatch(v -> v.matches(LATIN1));
    }
}
