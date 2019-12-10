package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "SingleChoiceConditionalQuestion{" +
                "condition=" + condition +
                ", conditionalQuestion=" + conditionalQuestion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleChoiceConditionalQuestion)) return false;
        if (!super.equals(o)) return false;
        SingleChoiceConditionalQuestion that = (SingleChoiceConditionalQuestion) o;
        return Objects.equals(condition, that.condition) &&
                Objects.equals(conditionalQuestion, that.conditionalQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), condition, conditionalQuestion);
    }
}
