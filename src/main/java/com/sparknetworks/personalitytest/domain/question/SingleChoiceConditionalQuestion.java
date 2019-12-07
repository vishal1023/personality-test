package com.sparknetworks.personalitytest.domain.question;

import java.util.List;

public class SingleChoiceConditionalQuestion extends SingleChoiceQuestion implements QuestionType {

    private Condition condition;
    private final Question conditionalQuestion;

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
