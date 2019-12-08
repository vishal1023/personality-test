package com.sparknetworks.personalitytest.domain.answer;

import java.util.List;

public class PersonalityTestAnswers implements TestAnswers{

    private String testId;
    private String userId;
    private List<Answer> answers;

    private PersonalityTestAnswers() {
    }

    public PersonalityTestAnswers(String testId, String userId, List<Answer> answers) {
        this.testId = testId;
        this.userId = userId;
        this.answers = answers;
    }

    public String getTestId() {
        return testId;
    }

    public String getUserId() {
        return userId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "PersonalityTestAnswers{" +
                "testId='" + testId + '\'' +
                ", userId='" + userId + '\'' +
                ", answers=" + answers +
                '}';
    }
}
