package com.example.MoneyMind.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class CustomExceptionHandler {
    //captura a exceção MoneyMindException e imprime no terminal
    @ExceptionHandler(MoneyMindException.class)
    public ResponseEntity<String> handleMoneyMindException(MoneyMindException e) {
        return ResponseEntity.status(e.getCode()).body(new PwsAlert(e.getCode(), e.getMessage()).toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleResourceThrowableExeption(Throwable e)
    {
        log.error("AN ERROR HAS BEEN IDENTIFIED -> " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new PwsAlert(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()).toString());
    }
}