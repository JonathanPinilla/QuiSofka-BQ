package com.quisofka.questions.infrastructure.drivenAdapters.data;

import com.quisofka.questions.infrastructure.drivenAdapters.util.validators.KnowledgeAreaEnum;
import com.quisofka.questions.infrastructure.drivenAdapters.util.validators.LevelEnum;
import com.quisofka.questions.infrastructure.drivenAdapters.util.validators.TypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique=true)
    private String description;
    private Map<String, Boolean> answers;
    @NotBlank(message="knowledgeArea is required")
    @NotNull(message ="knowledgeArea is required")
    @KnowledgeAreaEnum
    private String knowledgeArea;
    @NotBlank(message="descriptor is required")
    @NotNull(message ="descriptor is required")
    private String descriptor;
    @NotBlank(message="type is required")
    @NotNull(message ="type is required")
    @TypeEnum(message="type should be 'Multiple', 'Single' or 'truefalse'")
    private String type;
    @NotBlank(message="level is required")
    @NotNull(message ="level is required")
    @LevelEnum(message="level should be 'Initial', 'Basic' or 'Intermediate'")
    private String level;
}
