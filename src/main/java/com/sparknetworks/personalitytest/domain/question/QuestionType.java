package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleChoiceQuestion.class),
        @JsonSubTypes.Type(value = SingleChoiceConditionalQuestion.class),
        @JsonSubTypes.Type(value = NumberRangeQuestion.class),
})
public interface QuestionType {
}
