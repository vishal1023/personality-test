package com.sparknetworks.personalitytest.controller;

import com.sparknetworks.personalitytest.domain.Question;
import com.sparknetworks.personalitytest.service.PersonalityTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
