package com.sparknetworks.personalitytest.domain.answer;

import com.sparknetworks.personalitytest.repository.mongodb.PersonalityTestKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "personality_test_answers")
public class PersonalityTestAnswers implements TestAnswers{

    @Id
    private PersonalityTestKey personalityTestKey;
    private List<Answer> answers;

    private PersonalityTestAnswers() {
    }

    public PersonalityTestAnswers(PersonalityTestKey personalityTestKey, List<Answer> answers) {
        this.personalityTestKey = personalityTestKey;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public PersonalityTestKey getPersonalityTestKey() {
        return personalityTestKey;
    }

    @Override
    public String toString() {
        return "PersonalityTestAnswers{" +
                "personalityTestKey=" + personalityTestKey +
                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalityTestAnswers)) return false;
        PersonalityTestAnswers that = (PersonalityTestAnswers) o;
        return Objects.equals(personalityTestKey, that.personalityTestKey) &&
                Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalityTestKey, answers);
    }
}
