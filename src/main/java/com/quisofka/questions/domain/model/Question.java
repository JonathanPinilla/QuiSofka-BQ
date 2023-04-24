package com.quisofka.questions.domain.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Question {

    private String id;
    private String description;
    private Map<String, Boolean> answers;
    private String knowledgeArea;
    private String descriptor;
    //TODO: enums
    private String type;
    private String level;

}
