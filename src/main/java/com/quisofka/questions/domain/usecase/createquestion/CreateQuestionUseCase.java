package com.quisofka.questions.domain.usecase.createquestion;

import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateQuestionUseCase implements Function<Question, Mono<Question>> {

    private final QuestionRepositoryGateway repositoryGateway;

    @Override
    public Mono<Question> apply(Question question) {
        return repositoryGateway.createQuestion(question);
    }
}
