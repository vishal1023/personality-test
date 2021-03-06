package com.sparknetworks.personalitytest.service;

import com.sparknetworks.personalitytest.domain.answer.TestAnswers;
import com.sparknetworks.personalitytest.domain.question.PersonalityTestQuestions;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.exception.NotFoundException;
import com.sparknetworks.personalitytest.repository.PersonalityTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonalityTestService {

    @Autowired
    private PersonalityTestRepository personalityTestRepository;

    PersonalityTestService(PersonalityTestRepository personalityTestRepository) {
        this.personalityTestRepository = personalityTestRepository;
    }

    public PersonalityTestQuestions getAllQuestions() {
        List<Question> questions = personalityTestRepository.getAllQuestions();
        Set<String> categories = questions.stream().map(Question::getCategory).collect(Collectors.toSet());
        return new PersonalityTestQuestions(categories, questions);
    }

    public List<Question> getQuestionsFor(String category) {
        return personalityTestRepository.getQuestionsFor(category);
    }

    public void saveTestAnswers(TestAnswers answers) {
        personalityTestRepository.saveTestAnswer(answers);
    }

    public List<TestAnswers> getAllAns() {
        return personalityTestRepository.getAllAns();
    }

    public void deleteTestAnswers() {
        personalityTestRepository.deleteAllAnswers();
    }

    public void deleteQuestion(String questionId) {
        try {
            personalityTestRepository.deleteQuestion(questionId);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
