package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize
public class SingleChoiceConditionalQuestion extends SingleChoiceQuestion implements QuestionType {

    private Condition condition;
    private Question conditionalQuestion;

    private SingleChoiceConditionalQuestion() {
        super(null);
    }

    public SingleChoiceConditionalQuestion(List<String> options, Condition condition, Question conditionalQuestion) {
        super(options);
        this.condition = condition;
        this.conditionalQuestion = conditionalQuestion;
    }

    public Condition getCondition() {
        return condition;
    }

    public Question getConditionalQuestion() {
        return conditionalQuestion;
    }
}
