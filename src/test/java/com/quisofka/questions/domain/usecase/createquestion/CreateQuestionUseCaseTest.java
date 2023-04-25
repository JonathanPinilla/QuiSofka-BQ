package com.quisofka.questions.domain.usecase.createquestion;

import com.quisofka.questions.domain.model.Question;
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

import java.util.HashMap;


@ExtendWith(MockitoExtension.class)
class CreateQuestionUseCaseTest {

    @Mock
    QuestionRepositoryGateway repository;

    CreateQuestionUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new CreateQuestionUseCase(repository);
    }

    @Test
    @DisplayName("SaveQuestionUseCase_Success")
    void saveStudent(){
        var question = new Question(
                "id",
                "Question description",
                new HashMap<>(),
                "KnowledgeArea",
                "descriptor",
                "type",
                "level" );

        Mockito.when(repository.createQuestion(question)).thenReturn(Mono.just(question));

        var result = useCase.apply(question);

        StepVerifier.create(result)
                .expectNext(question)
                .expectComplete()
                .verify();

        Mockito.verify(repository, Mockito.times(1)).createQuestion(question);
    }

}