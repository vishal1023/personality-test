package com.sparknetworks.personalitytest.domain;

public class Question {

    private String questionText;
    private Category category;

    private Question() {
    }

    public Question(String questionText, Category category) {
        this.questionText = questionText;
        this.category = category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Category getCategory() {
        return category;
    }
}
