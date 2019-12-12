package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = SingleChoiceConditionalAnswer.class)
public class SingleChoiceConditionalAnswer extends SingleChoiceAnswer {

    private boolean isConditionSuccessful;
    private AnswerType conditionalAns;

    private SingleChoiceConditionalAnswer() {
        super(null, null);
    }

    public SingleChoiceConditionalAnswer(String ansType, String answerText, boolean isConditionSuccessful, AnswerType conditionalAns) {
        super(ansType, answerText);
        this.isConditionSuccessful = isConditionSuccessful;
        this.conditionalAns = conditionalAns;
    }

    public boolean isConditionSuccessful() {
        return isConditionSuccessful;
    }

    public AnswerType getConditionalAns() {
        return conditionalAns;
    }

    @Override
    public String toString() {
        return "SingleChoiceConditionalAnswer{" +
                "isConditionSuccessful=" + isConditionSuccessful +
                ", conditionalAns=" + conditionalAns +
                '}';
    }
}
