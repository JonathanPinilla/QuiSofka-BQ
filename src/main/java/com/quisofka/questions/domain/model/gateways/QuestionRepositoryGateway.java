package com.quisofka.questions.domain.model.gateways;

import com.quisofka.questions.domain.model.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionRepositoryGateway {

    Flux<Question> getAllQuestions();

    Mono<Question> getQuestionById(String id);
    Mono<Question> getQuestionByDescriptor(String id);
    Mono<Question> createQuestion(Question question);
    //Mono<Question> updateQuestion(String id, Question question);
    Mono<Void> deleteQuestionById(String id);
    Mono<Void> deleteAll();



}
