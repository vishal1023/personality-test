package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize
public class Predicate {

    @JsonProperty(value = "exactEquals")
    private List<String> exactEquals;

    private Predicate() {
    }

    public Predicate(List<String> exactEquals) {
        this.exactEquals = exactEquals;
    }

    public List<String> getExactEquals() {
        return exactEquals;
    }
}
