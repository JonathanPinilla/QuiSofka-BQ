package com.quisofka.questions.infrastructure.drivenAdapters.util.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = KnowledgeAreaEnumValidator.class)
public @interface KnowledgeAreaEnum {
    String message() default "Invalid knowledge area value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
