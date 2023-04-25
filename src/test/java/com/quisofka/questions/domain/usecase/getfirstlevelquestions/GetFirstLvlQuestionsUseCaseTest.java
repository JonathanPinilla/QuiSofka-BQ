package com.quisofka.questions.domain.usecase.getfirstlevelquestions;

import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFirstLvlQuestionsUseCaseTest {

    @Mock
    QuestionRepositoryGateway repository;

    GetFirstLvlQuestionsUseCase getFirstLvlQuestionsUseCase;

    @BeforeEach
    void setup() {
        getFirstLvlQuestionsUseCase = new GetFirstLvlQuestionsUseCase(repository);
    }

    @Test
    @DisplayName("getFirstLvlQuestions_Success")
    void get(){
        var fluxQuestions =Flux.just(
                new Question(
                        "id",
                        "Question description",
                        new HashMap<>(),
                        "KnowledgeArea",
                        "descriptor",
                        "type",
                        "level" ),
                new Question(
                        "id2",
                        "Question description 2",
                        new HashMap<>(),
                        "KnowledgeArea 2",
                        "descriptor 2",
                        "type 2",
                        "level 2" )
        );

        when(repository.getFirstLvlQuestions()).thenReturn(fluxQuestions);

        var service = getFirstLvlQuestionsUseCase.get();

        StepVerifier.create(service)
                .expectNextMatches(question -> question.getKnowledgeArea().equals("KnowledgeArea"))
                .expectNextCount(1)
                .verifyComplete();

        verify(repository).getFirstLvlQuestions();
    }

    @Test
    @DisplayName("getFirstLvlQuestions_NonSuccess")
    void getNonSuccess(){

        when(repository.getFirstLvlQuestions())
                .thenReturn(Flux.error(new Throwable("No questions available")));

        var service = getFirstLvlQuestionsUseCase.get();

        StepVerifier.create(service)
                .expectErrorMessage("No questions available")
                .verify();
        Mockito.verify(repository).getFirstLvlQuestions();
    }

}