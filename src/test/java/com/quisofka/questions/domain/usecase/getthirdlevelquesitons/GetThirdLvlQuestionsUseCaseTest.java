package com.quisofka.questions.domain.usecase.getthirdlevelquesitons;

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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetThirdLvlQuestionsUseCaseTest {

    @Mock
    QuestionRepositoryGateway repository;

    GetThirdLvlQuestionsUseCase getThirdLvlQuestionsUseCase;

    @BeforeEach
    void setup() {
        getThirdLvlQuestionsUseCase = new GetThirdLvlQuestionsUseCase(repository);
    }


    @Test
    @DisplayName("getThirdLvlQuestions_Success")
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

        when(repository.getThirdLvlQuestions()).thenReturn(fluxQuestions);

        var service = getThirdLvlQuestionsUseCase.get();

        StepVerifier.create(service)
                .expectNextMatches(question -> question.getKnowledgeArea().equals("KnowledgeArea"))
                .expectNextCount(1)
                .verifyComplete();

        verify(repository).getThirdLvlQuestions();
    }

    @Test
    @DisplayName("getThirdLvlQuestions_NonSuccess")
    void getNonSuccess(){

        when(repository.getThirdLvlQuestions())
                .thenReturn(Flux.error(new Throwable("No questions available")));

        var service = getThirdLvlQuestionsUseCase.get();

        StepVerifier.create(service)
                .expectErrorMessage("No questions available")
                .verify();
        Mockito.verify(repository).getThirdLvlQuestions();
    }

}