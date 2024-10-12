package com.example.MoneyMind.anotacoes;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidateSpecialCharacters implements ConstraintValidator<ContainsSpecialCharacter, String> {

    private static final String SPECIAL_CHAR_PATTERN = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";

    private final Pattern pattern = Pattern.compile(SPECIAL_CHAR_PATTERN);

    @Override
    public void initialize(ContainsSpecialCharacter constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        return pattern.matcher(password).matches();
    }
}