package com.sparknetworks.personalitytest.domain;

import java.util.List;

public class SingleChoiceQuestion implements QuestionType {

    private List<String> options;

    public SingleChoiceQuestion(List<String> options) {
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }
}
