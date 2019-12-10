package com.sparknetworks.personalitytest.domain.question;

import java.util.List;
import java.util.Objects;
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

    @Override
    public String toString() {
        return "PersonalityTestQuestions{" +
                "categories=" + categories +
                ", questions=" + questions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalityTestQuestions)) return false;
        PersonalityTestQuestions that = (PersonalityTestQuestions) o;
        return Objects.equals(categories, that.categories) &&
                Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories, questions);
    }
}
