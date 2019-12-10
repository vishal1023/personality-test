package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleChoiceQuestion.class, name = "single_choice"),
        @JsonSubTypes.Type(value = SingleChoiceConditionalQuestion.class, name = "single_choice_conditional"),
        @JsonSubTypes.Type(value = NumberRangeQuestion.class, name = "number_range"),
})
public interface QuestionType {
}
