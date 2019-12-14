package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "questions")
public class Question {

    @Id
    private String question;
    private String category;
    @JsonProperty("question_type")
    private QuestionType questionType;

    private Question() {
    }

    public Question(String question, String category, QuestionType questionType) {
        this.question = question;
        this.category = category;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
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
        return Objects.equals(this.question, question.question) &&
                Objects.equals(category, question.category) &&
                Objects.equals(questionType, question.questionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, category, questionType);
    }

    @Override
    public String toString() {
        return "Question{" +
                ", question='" + question + '\'' +
                ", category='" + category + '\'' +
                ", questionType=" + questionType +
                '}';
    }
}
