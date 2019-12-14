package com.sparknetworks.personalitytest.domain.answer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(as = PersonalityTestAnswers.class)
public interface TestAnswers {
}
