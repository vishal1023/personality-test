package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleChoiceAnswer.class),
        @JsonSubTypes.Type(value = SingleChoiceConditionalAnswer.class),
        @JsonSubTypes.Type(value = NumberRangeAnswer.class),
})
public interface AnswerType {
}
