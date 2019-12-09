package com.sparknetworks.personalitytest.repository.mongodb;

import com.sparknetworks.personalitytest.domain.answer.TestAnswers;
import org.springframework.data.mongodb.repository.MongoRepository;

interface AnswerRepository extends MongoRepository<TestAnswers, String> {
}
