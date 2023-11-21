package com.epam.ld.module2.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResponseExceptionMessage{

    private int statusCode;

    private HttpStatus status;

    private String message;

    public ResponseExceptionMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.statusCode = status.value();
    }

    public ResponseExceptionMessage(HttpStatusCode status, String message) {
        this.status = HttpStatus.valueOf(status.value());
        this.message = message;
        this.statusCode = status.value();
    }


}
