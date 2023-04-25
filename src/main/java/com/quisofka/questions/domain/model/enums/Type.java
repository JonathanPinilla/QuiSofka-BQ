package com.quisofka.questions.domain.model.enums;

public enum Type {

    MULTIPLE("Multiple choice"),
    SINGLE("Single choice"),
    TRUEFALSE("True or false");


    private String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
