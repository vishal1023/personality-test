package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = NumberRangeAnswer.class)
public class NumberRangeAnswer implements AnswerType {

    private String type;
    private int from;
    private int to;

    private NumberRangeAnswer() {
    }

    public NumberRangeAnswer(String type, int from, int to) {
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
