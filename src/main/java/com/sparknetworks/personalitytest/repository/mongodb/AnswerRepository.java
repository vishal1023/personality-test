package com.sparknetworks.personalitytest.repository.mongodb;

import com.sparknetworks.personalitytest.domain.answer.TestAnswers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerRepository extends MongoRepository<TestAnswers, PersonalityTestKey> {
}
