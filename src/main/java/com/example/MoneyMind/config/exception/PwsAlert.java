package com.example.MoneyMind.config.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PwsAlert implements Serializable {
    private static final long serialVersionUID = 1L;

    private HttpStatusCode code;
    private String message;
    private String description;

    public PwsAlert() {
    }

    public PwsAlert(HttpStatusCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public PwsAlert(HttpStatusCode code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }


    @JsonIgnore
    public static PwsAlert fromMap(Map<String, Object> map) {
        Object code = map.get("code");
        Object message = map.get("message");
        Object description = map.get("description");

        return new PwsAlert(
                code == null ? null : HttpStatus.valueOf(Integer.parseInt(code.toString())),
                message == null ? null : message.toString(),
                description == null ? null : description.toString()
        );
    }

    public HttpStatusCode getCode() {
        return code;
    }

    public void setCode(HttpStatusCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    @JsonIgnore
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code.value());
        map.put("message", message);
        map.put("description", description);
        return map;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}