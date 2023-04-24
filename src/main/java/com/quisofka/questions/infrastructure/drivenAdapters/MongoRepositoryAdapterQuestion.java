package com.quisofka.questions.infrastructure.drivenAdapters;


import com.quisofka.questions.domain.model.Question;
import com.quisofka.questions.domain.model.gateways.QuestionRepositoryGateway;
import com.quisofka.questions.infrastructure.drivenAdapters.data.QuestionData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterQuestion implements QuestionRepositoryGateway {

    private final MongoDBRepositoryQuestion questionRepository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Question> getAllQuestions() {
        return null;
    }

    @Override
    public Mono<Question> getQuestionById(String id) {
        return null;
    }

    @Override
    public Mono<Question> getQuestionByDescriptor(String id) {
        return null;
    }

    @Override
    public Mono<Question> createQuestion(Question question) {
        return Mono.just(question)
                .flatMap(question1 -> {
                    //question1.setCreatedAt(LocalDateTime.now());
                    //question1.setUpdatedAt(LocalDateTime.now());
                    //TODO: modify to calculate total... maybe another endpoint
                    //order1.calculateTotal();
                    return this.questionRepository.save(mapper.map(question1, QuestionData.class));
                }).map(question2 -> mapper.map(question2, Question.class))
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<Void> deleteQuestionById(String id) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }

}
