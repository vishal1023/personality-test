package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

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
}
