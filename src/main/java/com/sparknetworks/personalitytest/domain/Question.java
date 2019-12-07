package com.sparknetworks.personalitytest.domain;

public class Question {

    private String questionText;
    private String category;

    private Question() {
    }

    public Question(String questionText, String category) {
        this.questionText = questionText;
        this.category = category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCategory() {
        return category;
    }
}
