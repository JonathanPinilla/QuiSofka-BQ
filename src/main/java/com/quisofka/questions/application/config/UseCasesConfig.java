package com.quisofka.questions.application.config;

import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import com.quisofka.questions.domain.usecase.createquestion.CreateQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.quisofka.questions.domain")
public class UseCasesConfig {

    @Bean
    public CreateQuestionUseCase createQuestionUseCase(QuestionRepositoryGateway gateway){
        return new CreateQuestionUseCase(gateway);
    }
}
