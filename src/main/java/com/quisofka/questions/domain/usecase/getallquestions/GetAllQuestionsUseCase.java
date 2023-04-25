package com.quisofka.questions.domain.usecase.getallquestions;

import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllQuestionsUseCase implements Supplier<Flux<Question>> {

    private  final QuestionRepositoryGateway repositoryGateway;

    @Override
    public Flux<Question> get() {
        return repositoryGateway.getAllQuestions();
    }
}
