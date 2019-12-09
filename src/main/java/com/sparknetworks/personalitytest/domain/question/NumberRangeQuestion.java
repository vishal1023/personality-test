package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class NumberRangeQuestion implements QuestionType {

    private Range range;

    private NumberRangeQuestion() {
    }

    public NumberRangeQuestion(Range range) {
        this.range = range;
    }

    public Range getRange() {
        return range;
    }
}
