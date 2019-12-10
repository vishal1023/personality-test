package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "NumberRangeQuestion{" +
                "range=" + range +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberRangeQuestion)) return false;
        NumberRangeQuestion that = (NumberRangeQuestion) o;
        return Objects.equals(range, that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(range);
    }
}
