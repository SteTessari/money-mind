package com.example.moneymind.config.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoneyMindException extends RuntimeException {

    private final HttpStatusCode code;
    private final String message;

    public MoneyMindException(HttpStatusCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "MoneyMindException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
