package com.quisofka.questions.domain.model.enums;

public enum KnowledgeArea {
    Java("Java"),
    Javascript("Javascript"),
    DDD("DDD"),
    EMPRESARIAL("Arquitectura Empresarial");

    private String value;

    KnowledgeArea(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
