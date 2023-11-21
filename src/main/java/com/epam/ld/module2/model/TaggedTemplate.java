package com.epam.ld.module2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaggedTemplate {

    private String text;

    private Map<String, String> tags;

}
