package com.epam.ld.module2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Map;

/**
 * The type Client.
 */
@Value
@Builder
@With
public class Client {

    String email;

    String password;

    String targetEmail;

    String subject;

    Map<String, String> tags;


}
