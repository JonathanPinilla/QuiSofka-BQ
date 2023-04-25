package com.quisofka.questions.domain.usecase.getquestionbyid;

import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetQuestionByIdUseCase implements Function<String, Mono<Question>> {

    private  final QuestionRepositoryGateway repositoryGateway;

    @Override
    public Mono<Question> apply(String id) {
        return repositoryGateway.getQuestionById(id);
    }
}
