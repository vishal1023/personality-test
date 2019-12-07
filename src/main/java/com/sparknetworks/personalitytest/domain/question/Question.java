package com.sparknetworks.personalitytest.domain.question;

public class Question {

    private String questionText;
    private String category;
    private QuestionType questionType;

    private Question() {
    }

    public Question(String questionText, String category, QuestionType questionType) {
        this.questionText = questionText;
        this.category = category;
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCategory() {
        return category;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
