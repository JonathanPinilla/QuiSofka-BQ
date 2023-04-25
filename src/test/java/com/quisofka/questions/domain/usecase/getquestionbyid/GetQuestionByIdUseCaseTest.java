package com.quisofka.questions.domain.usecase.getquestionbyid;

import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import com.quisofka.questions.infrastructure.drivenAdapters.MongoDBRepositoryQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.HttpURLConnection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetQuestionByIdUseCaseTest {

    @Mock
    QuestionRepositoryGateway repository;

    GetQuestionByIdUseCase getQuestionByIdUseCase;
    ModelMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ModelMapper();
        getQuestionByIdUseCase = new GetQuestionByIdUseCase(repository);
    }

    @Test
    @DisplayName("getStudentById_Success")
    void getQuestionById(){
        var question = new Question(
                "id",
                "Question description",
                new HashMap<>(),
                "KnowledgeArea",
                "descriptor",
                "type",
                "level" );

        Mockito.when(
                repository.getQuestionById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(question));

        var service = getQuestionByIdUseCase.apply("id");

        StepVerifier.create(service)
                //.expectNextCount(1)
                .expectNext(mapper.map(question, Question.class))
                .verifyComplete();
        Mockito.verify(repository).getQuestionById("id");
    }

    @Test
    @DisplayName("getStudentById_NonSuccess")
    void getQuestionByNonExistingID(){
        var studentID = "ID1";

        Mockito.when(repository.getQuestionById(studentID))
                .thenReturn(Mono.error(new Throwable("No question available")));

        var service = getQuestionByIdUseCase.apply(studentID);

        StepVerifier.create(service)
                .expectErrorMessage("No question available")
                .verify();
        Mockito.verify(repository).getQuestionById(studentID);

    }

}