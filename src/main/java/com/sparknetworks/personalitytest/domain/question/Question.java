package com.sparknetworks.personalitytest.domain.question;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "questions")
public class Question {

    @Id
    private String id;
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

    public String getId() {
        return id;
    }
}
