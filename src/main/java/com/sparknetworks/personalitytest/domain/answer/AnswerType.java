package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleChoiceAnswer.class, name = "singleChoiceAnswer"),
        @JsonSubTypes.Type(value = SingleChoiceConditionalAnswer.class, name = "singleChoiceConditionalAnswer"),
        @JsonSubTypes.Type(value = NumberRangeAnswer.class, name = "numberRangeAnswer"),
})
public interface AnswerType {
}
