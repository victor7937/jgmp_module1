package com.epam.ld.module2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMailRequestDto {

    private String from;

    private String password;

    private String to;

    private String subject;

    private String text;

    private Map<String, String> tags;




}
