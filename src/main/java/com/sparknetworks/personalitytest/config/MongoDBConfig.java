package com.sparknetworks.personalitytest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.personalitytest.domain.question.PersonalityTestQuestions;
import com.sparknetworks.personalitytest.repository.mongodb.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.File;
import java.io.IOException;

@EnableMongoRepositories(basePackages = "com.sparknetworks.personalitytest.repository")
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(QuestionRepository repository) {
        return args -> {
            PersonalityTestQuestions personalityTestQuestion = getPersonalityTestQuestionFromJSON();
            personalityTestQuestion.getQuestions().forEach(repository::save);

//            QuestionType singleChoice = new SingleChoiceQuestion(asList("yes", "sometimes", "no"));
//            repository.save(new Question("How often do you smoke?", "lifestyle", singleChoice));
//            repository.save(new Question("What is your attitude towards drugs?", "lifestyle", singleChoice));
        };
    }

    private PersonalityTestQuestions getPersonalityTestQuestionFromJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonalityTestQuestions personalityTestQuestions = objectMapper.
                readValue(new File("D:/personality_test.json"), PersonalityTestQuestions.class);
        System.out.println("personalityTestQuestions = " + personalityTestQuestions.getQuestions().toString());
        return personalityTestQuestions;
    }
//    @Bean
//    CommandLineRunner commandLineRunner1(AnswerRepository repository) {
//        return args ->
//                repository.save(new PersonalityTestAnswers(new PersonalityTestKey("user1", "test1"), getAnswers()));
//    }
//
//    private List<Answer> getAnswers() {
//        AnswerType singleChoiceAnswer = new SingleChoiceAnswer("SingleChoice", "Yes");
//        AnswerType singleChoiceConditional = new SingleChoiceConditionalAnswer(
//                "single_choice_conditional", "Yes", true, singleChoiceAnswer);
//        AnswerType numberRangeAnswer = new NumberRangeAnswer("number_range", 25);
//
//        Answer answer1 = new Answer("a1", singleChoiceAnswer);
//        Answer answer2 = new Answer("b1", singleChoiceAnswer);
//        Answer answer3 = new Answer("c1", singleChoiceConditional);
//        Answer answer4 = new Answer("d1", numberRangeAnswer);
//
//        return Arrays.asList(answer1, answer2, answer3, answer4);
//    }
}
