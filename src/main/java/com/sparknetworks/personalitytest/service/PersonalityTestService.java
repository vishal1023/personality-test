package com.sparknetworks.personalitytest.service;

import com.sparknetworks.personalitytest.repository.PersonalityTestRepository;
import com.sparknetworks.personalitytest.domain.question.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalityTestService {

    private PersonalityTestRepository personalityTestRepository;

    PersonalityTestService(PersonalityTestRepository personalityTestRepository) {
        this.personalityTestRepository = personalityTestRepository;
    }

    public List<Question> getAllQuestions() {
        return personalityTestRepository.getAllQuestions();
    }

    public List<Question> getQuestionsFor(String category) {
        return personalityTestRepository.getQuestionsFor(category);
    }
}
