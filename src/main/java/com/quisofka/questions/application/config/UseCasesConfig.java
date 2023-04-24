package com.quisofka.questions.application.config;

import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import com.quisofka.questions.domain.usecase.createquestion.CreateQuestionUseCase;
import com.quisofka.questions.domain.usecase.deleteallquestions.DeleteAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getallquestions.GetAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getfirstlevelquestions.GetFirstLvlQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getquestionbyid.GetQuestionByIdUseCase;
import com.quisofka.questions.domain.usecase.getsecondelevelquestions.GetSecondLvlQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getthirdlevelquesitons.GetThirdLvlQuestionsUseCase;
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
    public GetFirstLvlQuestionsUseCase getFirstLvlQuestionsUseCase(QuestionRepositoryGateway gateway){
        return new GetFirstLvlQuestionsUseCase(gateway);
    }

    @Bean
    public GetSecondLvlQuestionsUseCase getSecondLvlQuestionsUseCase(QuestionRepositoryGateway gateway){
        return new GetSecondLvlQuestionsUseCase(gateway);
    }

    @Bean
    public GetThirdLvlQuestionsUseCase getThirdLvlQuestionsUseCase(QuestionRepositoryGateway gateway){
        return new GetThirdLvlQuestionsUseCase(gateway);
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
