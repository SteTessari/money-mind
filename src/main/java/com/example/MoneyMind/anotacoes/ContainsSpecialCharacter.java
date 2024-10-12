package com.example.MoneyMind.anotacoes;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateSpecialCharacters.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsSpecialCharacter {

    String message() default "Necessário adicionar pelo menos 1 caracter especial.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}