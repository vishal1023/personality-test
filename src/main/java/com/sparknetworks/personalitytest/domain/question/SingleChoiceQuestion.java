package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Objects;

@JsonDeserialize
public class SingleChoiceQuestion implements QuestionType {

    private List<String> options;

    private SingleChoiceQuestion() {
    }

    public SingleChoiceQuestion(List<String> options) {
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleChoiceQuestion)) return false;
        SingleChoiceQuestion that = (SingleChoiceQuestion) o;
        return Objects.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(options);
    }

    @Override
    public String toString() {
        return "SingleChoiceQuestion{" +
                "options=" + options +
                '}';
    }
}
