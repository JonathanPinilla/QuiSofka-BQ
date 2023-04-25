package com.quisofka.questions.infrastructure.entryPoints;

import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.usecase.createquestion.CreateQuestionUseCase;
import com.quisofka.questions.domain.usecase.deleteallquestions.DeleteAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getallquestions.GetAllQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getfirstlevelquestions.GetFirstLvlQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getquestionbyid.GetQuestionByIdUseCase;
import com.quisofka.questions.domain.usecase.getsecondelevelquestions.GetSecondLvlQuestionsUseCase;
import com.quisofka.questions.domain.usecase.getthirdlevelquesitons.GetThirdLvlQuestionsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RouterOperation(path = "/quisofka/questions", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllQuestionsUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getAll", tags = "Question usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Question.class))),
                            @ApiResponse(responseCode = "204", description = "No questions found")
                    }))
    public RouterFunction<ServerResponse> getAll(GetAllQuestionsUseCase getAllQuestionsUseCase){
        return route(GET("/quisofka/questions"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllQuestionsUseCase.get(), Question.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/quisofka/questions/first", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetFirstLvlQuestionsUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getFirstLvlQuestions", tags = "Question usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Question.class))),
                            @ApiResponse(responseCode = "204", description = "No questions found")
                    }))
    public RouterFunction<ServerResponse> getFirstLvlQuestions(GetFirstLvlQuestionsUseCase getFirstLvlQuestionsUseCase){
        return route(GET("/quisofka/questions/first"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getFirstLvlQuestionsUseCase.get(), Question.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/quisofka/questions/second", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetSecondLvlQuestionsUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getSecondLvlQuestions", tags = "Question usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Question.class))),
                            @ApiResponse(responseCode = "204", description = "No questions found")
                    }))
    public RouterFunction<ServerResponse> getSecondLvlQuestions(GetSecondLvlQuestionsUseCase getSecondLvlQuestionsUseCase){
        return route(GET("/quisofka/questions/second"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getSecondLvlQuestionsUseCase.get(), Question.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/quisofka/questions/third", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetThirdLvlQuestionsUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getThirdLvlQuestions", tags = "Question usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Question.class))),
                            @ApiResponse(responseCode = "204", description = "No questions found")
                    }))
    public RouterFunction<ServerResponse> getThirdLvlQuestions(GetThirdLvlQuestionsUseCase getThirdLvlQuestionsUseCase){
        return route(GET("/quisofka/questions/third"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getThirdLvlQuestionsUseCase.get(), Question.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/quisofka/questions/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetQuestionByIdUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getQuestionById", tags = "Question usecases",
                    parameters = {@Parameter(name = "id", description = "question Id", required = true, in = ParameterIn.PATH)},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Question.class))),
                            @ApiResponse(responseCode = "404", description = "Question not Found")
                    }))
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
    @RouterOperation(path = "/quisofka/questions", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = CreateQuestionUseCase.class, method = RequestMethod.POST,
            beanMethod = "apply",
            operation = @Operation(operationId = "createQuestion", tags = "Question usecases",
                    parameters ={@Parameter(
                            name = "question",
                            in = ParameterIn.PATH,
                            schema =@Schema(implementation = Question.class))
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Question.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable, Try again")
                    },
                    requestBody = @RequestBody(
                            required=true,
                            description= "Create question",
                            content = @Content(schema = @Schema(implementation = Question.class)))))
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
    @RouterOperation(path = "/quisofka/questions", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteAllQuestionsUseCase.class, method = RequestMethod.DELETE,
            beanMethod = "get",
            operation = @Operation(operationId = "deleteAllQuestions", tags = "Question usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Question.class))),
                            @ApiResponse(responseCode = "204", description = "No questions found")
                    }))
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
