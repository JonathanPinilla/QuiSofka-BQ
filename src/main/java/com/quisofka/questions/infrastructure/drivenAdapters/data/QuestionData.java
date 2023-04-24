package com.quisofka.questions.infrastructure.drivenAdapters.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Data
@Document(collection = "questions")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionData {


    @Id
    private String id = UUID.randomUUID().toString().substring(0,10);
    @NotBlank(message="description is required")
    @NotNull(message ="description is required")
    private String description;
    private Map<String, Boolean> answers;
    private String knowledgeArea;
    private String descriptor;
    //TODO: do respective enums
    private String type;
    private String level;
}
