package com.quisofka.questions.domain.model.gateways;

import com.quisofka.questions.domain.model.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionRepositoryGateway {

    Flux<Question> getAllQuestions();
    Flux<Question> getFirstLvlQuestions();
    Flux<Question> getSecondLvlQuestions();
    Flux<Question> getThirdLvlQuestions();
    Mono<Question> getQuestionById(String id);
    Mono<Question> createQuestion(Question question);
    Mono<Void> deleteAll();

}
