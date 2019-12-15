package com.sparknetworks.personalitytest.controller;

import com.sparknetworks.personalitytest.domain.answer.TestAnswers;
import com.sparknetworks.personalitytest.domain.question.PersonalityTestQuestions;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.exception.CategoryNotFoundException;
import com.sparknetworks.personalitytest.service.PersonalityTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class PersonalityTestController {

    private static final Logger logger = LoggerFactory.getLogger(PersonalityTestController.class);

    @Autowired
    private PersonalityTestService personalityTestService;

    @GetMapping("/personality-test/questions")
    public PersonalityTestQuestions getAllQuestions() {
        logger.info("Received request to get All Question ");
        return personalityTestService.getAllQuestions();
    }

    @GetMapping("/personality-test/questions/categories/{category}")
    public List<Question> getQuestionsFor(@PathVariable(value = "category") String category) {
        logger.info("Received request to get Question of " + category);
        List<Question> questions = personalityTestService.getQuestionsFor(category);
        if (questions == null || questions.size() == 0) {
            throw new CategoryNotFoundException();
        }
        return questions;
    }

    @PostMapping("/personality-test/answers")
    public ResponseEntity<?> saveAnswer(@RequestBody TestAnswers personalityTestAnswers) {
        logger.info("Received request to save answer ");
        personalityTestService.saveTestAnswers(personalityTestAnswers);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/personality-test/answers")
    public List<TestAnswers> getAllAns() {
        logger.info("Received request to get All Answers");
        return personalityTestService.getAllAns();
    }

    @DeleteMapping("/personality-test/answers")
    public ResponseEntity deleteTestAnswer() {
        logger.info("Received request to delete All Answers");
        personalityTestService.deleteTestAnswers();
        return new ResponseEntity(OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handle(com.mongodb.MongoWriteException e) {
        logger.error("Answer for this test and user already present, can not insert" + e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequest(HttpMessageNotReadableException e) {
        logger.error("Returning HTTP 400 Bad Request" + e.getMessage());
    }
}
