package com.example.moneymind.anotacoes;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidateCapitalLetter implements ConstraintValidator<ContainsCapitalLetter, String> {

    private static final String UPPER_CASE_PATTERN = ".*[A-Z].*";

    private final Pattern pattern = Pattern.compile(UPPER_CASE_PATTERN);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return pattern.matcher(password).matches();
    }
}