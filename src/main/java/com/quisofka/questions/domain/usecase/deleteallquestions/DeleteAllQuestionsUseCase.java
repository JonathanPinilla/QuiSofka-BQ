package com.quisofka.questions.domain.usecase.deleteallquestions;

import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class DeleteAllQuestionsUseCase implements Supplier<Mono<Void>> {

    private  final QuestionRepositoryGateway repositoryGateway;


    @Override
    public Mono<Void> get() {
        return repositoryGateway.deleteAll();
    }
}
