package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Condition {

    @JsonProperty("predicate")
    private Predicate predicate;

    @JsonProperty(value = "if_positive")
    private Question ifPositive;

    private Condition() {
    }

    public Condition(Predicate predicate, Question ifPositive) {
        this.predicate = predicate;
        this.ifPositive = ifPositive;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public Question getIfPositive() {
        return ifPositive;
    }
}
