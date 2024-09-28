package com.example.MoneyMind.config.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoneyMindException extends RuntimeException {

    private HttpStatusCode code;
    private String message;
    private String description;

    public MoneyMindException(HttpStatusCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public MoneyMindException(HttpStatusCode code, String message, String description) {
        super(description);
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
