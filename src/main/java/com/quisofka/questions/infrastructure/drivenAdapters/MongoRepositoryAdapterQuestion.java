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
    public Flux<Question> getAllQuestions(){
        return this.questionRepository
                .findAll()
                .switchIfEmpty(Mono.error(new Throwable("No questions available")))
                .map(item -> mapper.map(item, Question.class))
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<Question> getQuestionById(String id) {
        return this.questionRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new Throwable("Question not found")))
                .map(item -> mapper.map(item, Question.class));
    }

    @Override
    public Mono<Question> getQuestionByDescriptor(String id) {
        return null;
    }

    @Override
    public Mono<Question> createQuestion(Question question) {
        return Mono.just(question)
                .flatMap(question1 -> {
                    //TODO: modify to calculate total... maybe another endpoint
                    return this.questionRepository.save(mapper.map(question1, QuestionData.class));
                }).map(question2 -> mapper.map(question2, Question.class))
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<Void> deleteQuestionById(String id) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(){
        return this.questionRepository.deleteAll()
                .onErrorResume(Mono::error);
    }

}
