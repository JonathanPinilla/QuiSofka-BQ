package com.quisofka.questions.infrastructure.drivenAdapters.util.validators;

import com.quisofka.questions.domain.model.enums.Level;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class LevelEnumValidator implements ConstraintValidator<LevelEnum, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(Level.values())
                .map(Level::name)
                .anyMatch(name -> name.equalsIgnoreCase(value));
    }
}
