package com.sparknetworks.personalitytest.domain.question;

public class NumberRangeQuestion implements QuestionType {

    private Range range;

    public NumberRangeQuestion(Range range) {
        this.range = range;
    }

    public Range getRange() {
        return range;
    }
}
