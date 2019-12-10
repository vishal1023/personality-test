package com.sparknetworks.personalitytest;

import com.sparknetworks.personalitytest.domain.answer.*;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.repository.mongodb.PersonalityTestKey;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Ignore
    @Test
    public void shouldGetListOfQuestionForAllCategories() {

        ResponseEntity<Question[]> responseEntity =
                this.restTemplate.getForEntity("/personality-test/questions", Question[].class);

        Question[] questions = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(questions).length).isGreaterThan(0);
    }

    @Ignore
    @Test
    public void shouldSaveTestAnswers() {
        List<Answer> answers = getAnswers();
        TestAnswers request = new PersonalityTestAnswers(new PersonalityTestKey("test1","user1"),answers);
        ResponseEntity<ResponseEntity> responseEntity =
                restTemplate.postForEntity("/personality-test/answers", request, ResponseEntity.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private List<Answer> getAnswers() {
        AnswerType singleChoiceAnswer = new SingleChoiceAnswer("SingleChoice", "Yes");
        AnswerType singleChoiceConditional = new SingleChoiceConditionalAnswer(
                "single_choice_conditional", "Yes", true, singleChoiceAnswer);
        AnswerType numberRangeAnswer = new NumberRangeAnswer("number_range", 25);

        Answer answer1 = new Answer("a1", singleChoiceAnswer);
        Answer answer2 = new Answer("b1", singleChoiceAnswer);
        Answer answer3 = new Answer("c1", singleChoiceConditional);
        Answer answer4 = new Answer("d1", numberRangeAnswer);

        return Arrays.asList(answer1, answer2, answer3, answer4);
    }
}
