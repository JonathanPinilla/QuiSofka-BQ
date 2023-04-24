package com.quisofka.questions.domain.usecase.deletebyid;

import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteQuesitonByIdUseCase implements Function<String, Mono<Void>> {

    private  final QuestionRepositoryGateway repositoryGateway;

    @Override
    public Mono<Void> apply(String id) {
        return repositoryGateway.deleteQuestionById(id);
    }
}
