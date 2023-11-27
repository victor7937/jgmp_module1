package com.epam.ld.module2.model;

import lombok.*;

@Value
@Builder
public class Message {
    String id;
    String content;
}
