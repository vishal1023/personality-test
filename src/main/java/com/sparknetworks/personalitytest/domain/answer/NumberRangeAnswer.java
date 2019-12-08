package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class NumberRangeAnswer implements AnswerType {

    private String type;
    private int answer;

    private NumberRangeAnswer() {
    }

    public NumberRangeAnswer(String type, int answer) {
        this.type = type;
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "NumberRangeAnswer{" +
                "type='" + type + '\'' +
                ", answer=" + answer +
                '}';
    }
}
