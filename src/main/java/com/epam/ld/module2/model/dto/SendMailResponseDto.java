package com.epam.ld.module2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMailResponseDto {
    private String messageId;
    private String messageText;
    private String from;
    private String to;
}
