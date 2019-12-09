package com.sparknetworks.personalitytest.repository.mongodb;

import com.sparknetworks.personalitytest.domain.question.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
