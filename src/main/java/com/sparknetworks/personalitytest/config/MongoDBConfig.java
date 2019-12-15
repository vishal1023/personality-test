package com.sparknetworks.personalitytest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.personalitytest.domain.question.PersonalityTestQuestions;
import com.sparknetworks.personalitytest.repository.mongodb.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.File;
import java.io.IOException;

@EnableMongoRepositories(basePackages = "com.sparknetworks.personalitytest.repository")
@Configuration
class MongoDBConfig {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBConfig.class);

    @Bean
    CommandLineRunner commandLineRunner(QuestionRepository repository) {
        return args -> {
            PersonalityTestQuestions personalityTestQuestion = getPersonalityTestQuestionFromJSON();
            personalityTestQuestion.getQuestions().forEach(repository::save);
        };
    }

    private PersonalityTestQuestions getPersonalityTestQuestionFromJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonalityTestQuestions personalityTestQuestions = objectMapper.
                readValue(new File("src\\main\\resources\\personality_test_initial_questions.json"), PersonalityTestQuestions.class);
        logger.info("personalityTestQuestions = " + personalityTestQuestions.getQuestions().toString());
        return personalityTestQuestions;
    }
}
