package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PersonalityTestAnswers.class, name = "personalityTestAnswers"),
})
public interface TestAnswers {
}
