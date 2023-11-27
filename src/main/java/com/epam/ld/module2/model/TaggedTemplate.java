package com.epam.ld.module2.model;

import lombok.*;

import java.util.Map;

@Value
public class TaggedTemplate {

    String text;

    Map<String, String> tags;

}
