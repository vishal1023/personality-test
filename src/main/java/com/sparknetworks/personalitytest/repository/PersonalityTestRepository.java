package com.sparknetworks.personalitytest.repository;

import com.sparknetworks.personalitytest.domain.answer.TestAnswers;
import com.sparknetworks.personalitytest.domain.question.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalityTestRepository {

    List<Question> getAllQuestions();

    List<Question> getQuestionsFor(String category);

    void saveTestAnswer(TestAnswers testAnswers);

    List<String> getCategories();
}
