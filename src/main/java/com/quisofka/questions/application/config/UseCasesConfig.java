package com.quisofka.questions.application.config;

import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import com.quisofka.questions.domain.usecase.createquestion.CreateQuestionUseCase;
import com.quisofka.questions.domain.usecase.deleteallquestions.DeleteAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getallquestions.GetAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getquestionbyid.GetQuestionByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.quisofka.questions.domain")
public class UseCasesConfig {

    @Bean
    public GetAllQuestionsUseCase getAllQuestionsUseCase(QuestionRepositoryGateway gateway){
        return new GetAllQuestionsUseCase(gateway);
    }

    @Bean
    public GetQuestionByIdUseCase getQuestionByIdUseCase(QuestionRepositoryGateway gateway){
        return new GetQuestionByIdUseCase(gateway);
    }

    @Bean
    public CreateQuestionUseCase createQuestionUseCase(QuestionRepositoryGateway gateway){
        return new CreateQuestionUseCase(gateway);
    }

    @Bean
    public DeleteAllQuestionsUseCase deleteAllQuestionsUseCase(QuestionRepositoryGateway gateway){
        return new DeleteAllQuestionsUseCase(gateway);
    }
}
