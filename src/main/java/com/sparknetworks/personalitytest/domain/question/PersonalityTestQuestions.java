package com.sparknetworks.personalitytest.domain.question;

import java.util.List;
import java.util.Set;

public class PersonalityTestQuestions {
    private Set<String> categories;
    private List<Question> questions;

    private PersonalityTestQuestions() {
    }

    public PersonalityTestQuestions(Set<String> categories, List<Question> questions) {
        this.categories = categories;
        this.questions = questions;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
