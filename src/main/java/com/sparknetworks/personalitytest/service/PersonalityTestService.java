package com.sparknetworks.personalitytest.service;

import com.sparknetworks.personalitytest.domain.answer.TestAnswers;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.repository.PersonalityTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalityTestService {

    @Autowired
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

    public void saveTestAnswers(TestAnswers answers) {
        personalityTestRepository.saveTestAnswer(answers);
    }
}
