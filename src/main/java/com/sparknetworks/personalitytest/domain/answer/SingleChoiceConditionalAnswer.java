package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = SingleChoiceConditionalAnswer.class)
public class SingleChoiceConditionalAnswer extends SingleChoiceAnswer {

    private AnswerType conditionalAns;

    private SingleChoiceConditionalAnswer() {
        super(null, null);
    }

    public SingleChoiceConditionalAnswer(String ansType, String answerText, AnswerType conditionalAns) {
        super(ansType, answerText);
        this.conditionalAns = conditionalAns;
    }

    public AnswerType getConditionalAns() {
        return conditionalAns;
    }

    @Override
    public String toString() {
        return "SingleChoiceConditionalAnswer{" +
                ", conditionalAns=" + conditionalAns +
                '}';
    }
}
