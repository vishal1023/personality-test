package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "questions")
public class Question {

    @Id
    private String questionText;
    private String category;
    @JsonProperty("question_type")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(questionText, question.questionText) &&
                Objects.equals(category, question.category) &&
                Objects.equals(questionType, question.questionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, category, questionType);
    }

    @Override
    public String toString() {
        return "Question{" +
                ", questionText='" + questionText + '\'' +
                ", category='" + category + '\'' +
                ", questionType=" + questionType +
                '}';
    }
}
