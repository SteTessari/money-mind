package com.example.MoneyMind.anotacoes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidateLowercaseLetter implements ConstraintValidator<ContainsLowercaseLetter, String> {

    private static final String LOWER_CASE_PATTERN = ".*[a-z].*";

    private final Pattern pattern = Pattern.compile(LOWER_CASE_PATTERN);

    @Override
    public void initialize(ContainsLowercaseLetter constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        return pattern.matcher(password).matches();
    }
}