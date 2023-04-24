package com.quisofka.questions.infrastructure.drivenAdapters;

import com.quisofka.questions.infrastructure.drivenAdapters.data.QuestionData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;



public interface MongoDBRepositoryQuestion extends ReactiveMongoRepository<QuestionData, String> {
}
