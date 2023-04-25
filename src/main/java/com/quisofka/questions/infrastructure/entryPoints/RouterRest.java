package com.quisofka.questions.infrastructure.entryPoints;

import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.usecase.createquestion.CreateQuestionUseCase;
import com.quisofka.questions.domain.usecase.deleteallquestions.DeleteAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getallquestions.GetAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getfirstlevelquestions.GetFirstLvlQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getquestionbyid.GetQuestionByIdUseCase;
import com.quisofka.questions.domain.usecase.getsecondelevelquestions.GetSecondLvlQuestionsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterRest {


    @Bean
    public RouterFunction<ServerResponse> getAll(GetAllQuestionsUseCase getAllQuestionsUseCase){
        return route(GET("/quisofka/questions"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllQuestionsUseCase.get(), Question.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllQuestions(GetFirstLvlQuestionsUseCase getFirstLvlQuestionsUseCase){
        return route(GET("/quisofka/questions/first"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getFirstLvlQuestionsUseCase.get(), Question.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

    @Bean
    public RouterFunction<ServerResponse> getSecondLvlQuestions(GetSecondLvlQuestionsUseCase getSecondLvlQuestionsUseCase){
        return route(GET("/quisofka/questions/second"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getSecondLvlQuestionsUseCase.get(), Question.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }


    @Bean
    public RouterFunction<ServerResponse> getQuestionById(GetQuestionByIdUseCase getQuestionByIdUseCase){
        return route(GET("/quisofka/questions/{id}"),
                request -> getQuestionByIdUseCase.apply(request.pathVariable("id"))
                        .switchIfEmpty(Mono.error(new Throwable(HttpStatus.NO_CONTENT.toString())))
                        .flatMap(question -> ServerResponse.status(200)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(question))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> createQuestion(CreateQuestionUseCase createQuestionUseCase) {
        return route(POST("/quisofka/questions").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Question.class)
                        .flatMap(question -> createQuestionUseCase.apply(question)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(throwable.getMessage()))));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteAllQuestions(DeleteAllQuestionsUseCase deleteAllQuestionsUseCase){
        return route(DELETE("/quisofka/questions"),
                request -> deleteAllQuestionsUseCase.get()
                        .thenReturn(
                                ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(Collections.singletonMap("message", "All questions have been deleted")))
                        .flatMap(serverResponseMono -> serverResponseMono)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

}
