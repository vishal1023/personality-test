package com.sparknetworks.personalitytest;

import com.sparknetworks.personalitytest.domain.answer.*;
import com.sparknetworks.personalitytest.domain.question.PersonalityTestQuestions;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.domain.question.SingleChoiceQuestion;
import com.sparknetworks.personalitytest.repository.mongodb.PersonalityTestKey;
import com.sparknetworks.personalitytest.repository.mongodb.PersonalityTestMongoDBRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PersonalityTestMongoDBRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        repository.deleteAllQuestions();
        repository.deleteAllAnswers();
    }

    @After
    public void tearDown() {
        repository.deleteAllQuestions();
        repository.deleteAllAnswers();
    }

    @Test
    public void shouldGetListOfQuestionForAllCategories() {
        repository.addQuestion(new Question("What is your gender", "hard-fact",
                new SingleChoiceQuestion(Arrays.asList("male", "female", "other"))));

        ResponseEntity<PersonalityTestQuestions> responseEntity =
                this.restTemplate.getForEntity("/personality-test/questions", PersonalityTestQuestions.class);


        PersonalityTestQuestions questions = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assert questions != null;
        assertThat(questions.getQuestions().size()).isEqualTo(1);
    }


    @Test
    public void shouldSaveTestAnswers() {
        deleteTestAnswerIfPresent();
        List<Answer> answers = getAnswers();
        TestAnswers request = new PersonalityTestAnswers(new PersonalityTestKey("test1", "user1"), answers);
        ResponseEntity<ResponseEntity> responseEntity =
                restTemplate.postForEntity("/personality-test/answers", request, ResponseEntity.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private void deleteTestAnswerIfPresent() {
        restTemplate.delete("/personality-test/answers");
    }

    private List<Answer> getAnswers() {
        AnswerType singleChoiceAnswer = new SingleChoiceAnswer("SingleChoice", "Yes");
        AnswerType singleChoiceConditional = new SingleChoiceConditionalAnswer(
                "single_choice_conditional", "Yes", singleChoiceAnswer);
        AnswerType numberRangeAnswer = new NumberRangeAnswer("number_range", 25, 30);

        Answer answer1 = new Answer("a1", singleChoiceAnswer);
        Answer answer2 = new Answer("b1", singleChoiceAnswer);
        Answer answer3 = new Answer("c1", singleChoiceConditional);
        Answer answer4 = new Answer("d1", numberRangeAnswer);

        return Arrays.asList(answer1, answer2, answer3, answer4);
    }

    @Test
    public void shouldReturnAllAnswers() {
        ResponseEntity<TestAnswers[]> responseEntity =
                this.restTemplate.getForEntity("/personality-test/answers", TestAnswers[].class);

        TestAnswers[] body = responseEntity.getBody();
        System.out.println("*********************************");
        System.out.println(Arrays.toString(body));
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
