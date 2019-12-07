package com.sparknetworks.personalitytest.repository;

import com.sparknetworks.personalitytest.domain.question.Question;

import java.util.List;

public interface PersonalityTestRepository {

    List<Question> getAllQuestions();

    List<Question> getQuestionsFor(String category);
}
