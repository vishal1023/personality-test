package com.sparknetworks.personalitytest.repository.mongodb;

import java.util.Objects;

public class PersonalityTestKey {

    private String userId;
    private String testId;

    public PersonalityTestKey(String userId, String testId) {
        this.userId = userId;
        this.testId = testId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTestId() {
        return testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalityTestKey)) return false;
        PersonalityTestKey that = (PersonalityTestKey) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, testId);
    }
}
