package com.example.moneymind.anotacoes;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateLowercaseLetter.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsLowercaseLetter {

    String message() default "A senha deve conter pelo menos uma letra minúscula";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}