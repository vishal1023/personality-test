package com.sparknetworks.personalitytest.controller;

import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.exception.CategoryNotFoundException;
import com.sparknetworks.personalitytest.service.PersonalityTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonalityTestController {

    @Autowired
    private PersonalityTestService personalityTestService;

    @GetMapping("/personality-test/questions")
    public List<Question> getAllQuestions() {
        return personalityTestService.getAllQuestions();
    }

    @GetMapping("/personality-test/questions/categories/{category}")
    public List<Question> getQuestionsFor(@PathVariable(value = "category") String category) {
        List<Question> questions = personalityTestService.getQuestionsFor(category);
        if (questions == null || questions.size() == 0) {
            throw new CategoryNotFoundException();
        }
        return questions;
    }
}
