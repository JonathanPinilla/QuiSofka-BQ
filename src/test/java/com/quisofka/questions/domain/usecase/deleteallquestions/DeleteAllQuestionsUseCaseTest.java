package com.quisofka.questions.domain.usecase.deleteallquestions;

import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteAllQuestionsUseCaseTest {

    @Mock
    QuestionRepositoryGateway repository;

    DeleteAllQuestionsUseCase deleteAllQuestions;

    @BeforeEach
    void setup() {
        deleteAllQuestions = new DeleteAllQuestionsUseCase(repository);
    }

    @Test
    @DisplayName("deleteQuestions_Success")
    void deleteQuestion(){

        var monoVoid = Mono.empty().then();

        Mockito.when(repository.deleteAll()).thenReturn(monoVoid);

        var service = deleteAllQuestions.get();

        StepVerifier.create(service)
                .expectComplete()
                .verify();
        Mockito.verify(repository).deleteAll();
    }

}