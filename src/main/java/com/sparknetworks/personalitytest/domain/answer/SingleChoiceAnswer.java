package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class SingleChoiceAnswer implements AnswerType {

    private String type;
    private String answerText; 

    private SingleChoiceAnswer() {
    }

    public SingleChoiceAnswer(String type, String answerText) {
        this.type = type;
        this.answerText = answerText;
    }

    public String getType() {
        return type;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public String toString() {
        return "SingleChoiceAnswer{" +
                "type='" + type + '\'' +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
