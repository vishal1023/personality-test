package com.sparknetworks.personalitytest.domain;

import java.util.List;

public interface PersonalityTestRepository {

    List<Question> getAllQuestions();

    List<Question> getQuestionsFor(String category);
}
