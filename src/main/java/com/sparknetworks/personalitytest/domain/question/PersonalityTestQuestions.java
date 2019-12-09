package com.sparknetworks.personalitytest.domain.question;

import java.util.List;

public class PersonalityTestQuestions {
    private List<String> categories;
    private List<Question> questions;

    private PersonalityTestQuestions() {
    }

    public PersonalityTestQuestions(List<String> categories, List<Question> questions) {
        this.categories = categories;
        this.questions = questions;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
