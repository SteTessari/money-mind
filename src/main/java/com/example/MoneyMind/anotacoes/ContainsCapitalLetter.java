package com.example.MoneyMind.anotacoes;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateCapitalLetter.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsCapitalLetter {

    String message() default "A senha deve conter pelo menos uma letra maiúscula";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}