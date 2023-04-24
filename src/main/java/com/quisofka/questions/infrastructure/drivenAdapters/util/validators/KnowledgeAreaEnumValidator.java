package com.quisofka.questions.infrastructure.drivenAdapters.util.validators;

import com.quisofka.questions.domain.model.enums.KnowledgeArea;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class KnowledgeAreaEnumValidator implements ConstraintValidator<KnowledgeAreaEnum, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(KnowledgeArea.values())
                .map(KnowledgeArea::name)
                .anyMatch(name -> name.equalsIgnoreCase(value));
    }
}
