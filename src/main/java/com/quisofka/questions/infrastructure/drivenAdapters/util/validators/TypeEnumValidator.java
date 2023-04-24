package com.quisofka.questions.infrastructure.drivenAdapters.util.validators;

import com.quisofka.questions.domain.model.enums.Type;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class TypeEnumValidator implements ConstraintValidator<TypeEnum, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(Type.values())
                .map(Type::name)
                .anyMatch(name -> name.equalsIgnoreCase(value));
    }
}
