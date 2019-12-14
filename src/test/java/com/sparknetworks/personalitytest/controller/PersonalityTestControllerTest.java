package com.sparknetworks.personalitytest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.personalitytest.domain.answer.*;
import com.sparknetworks.personalitytest.domain.question.PersonalityTestQuestions;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.domain.question.QuestionType;
import com.sparknetworks.personalitytest.domain.question.SingleChoiceQuestion;
import com.sparknetworks.personalitytest.repository.mongodb.PersonalityTestKey;
import com.sparknetworks.personalitytest.service.PersonalityTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonalityTestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalityTestService personalityTestService;
    private QuestionType singleChoice;

    @Before
    public void setUp() {
        singleChoice = new SingleChoiceQuestion(asList("yes", "sometimes", "no"));
    }

    @Test
    public void shouldGetListOfQuestion() throws Exception {
        List<Question> questions = asList(
                new Question("How should your potential partner respond to this question?", "hard_fact", singleChoice),
                new Question("Do any children under the age of 18 live with you?", "lifestyle", singleChoice)
        );
        Set<String> categories = new HashSet<>(asList("hard_fact", "lifestyle"));
        PersonalityTestQuestions personalityTestQuestions = new PersonalityTestQuestions(categories, questions);
        when(this.personalityTestService.getAllQuestions()).thenReturn(personalityTestQuestions);

        mockMvc.perform(get("/personality-test/questions"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.categories", hasSize(2)))
                .andExpect(jsonPath("$.questions", hasSize(2)))
                .andExpect(jsonPath("$.categories", hasItem("hard_fact")))
                .andExpect(jsonPath("$.categories", hasItem("lifestyle")))
                .andExpect(jsonPath("$.questions[0].question")
                        .value("How should your potential partner respond to this question?"))
                .andExpect(jsonPath("$.questions[0]category").value("hard_fact"))
                .andExpect(jsonPath("$.questions[1].question")
                        .value("Do any children under the age of 18 live with you?"))
                .andExpect(jsonPath("$.questions[1]category").value("lifestyle"));
    }

    @Test
    public void shouldGetEmptyListOfQuestionIfNoQuestionsFound() throws Exception {
        when(this.personalityTestService.getAllQuestions())
                .thenReturn(new PersonalityTestQuestions(emptySet(), emptyList()));

        mockMvc.perform(get("/personality-test/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questions", hasSize(0)));
    }

    @Test
    public void shouldGetListOfQuestionsForGivenCategory() throws Exception {
        List<Question> questions = asList(
                new Question("How often do you smoke?", "lifestyle", singleChoice),
                new Question("What is your attitude towards drugs?", "lifestyle", singleChoice)
        );
        when(this.personalityTestService.getQuestionsFor("lifestyle")).thenReturn(questions);

        this.mockMvc.perform(get("/personality-test/questions/categories/lifestyle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value("lifestyle"));
    }

    @Test
    public void shouldReturnCategoryNotFound() throws Exception {
        when(this.personalityTestService.getQuestionsFor("unknown-category")).thenReturn(emptyList());

        this.mockMvc.perform(get("/personality-test/questions/categories/unknown-category"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldSaveAllAnswersOfTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        doNothing().when(personalityTestService).saveTestAnswers(any());
        TestAnswers personalityTestAnswers = new PersonalityTestAnswers(new PersonalityTestKey("test1", "100"), getAnswers());

        this.mockMvc.perform(post("/personality-test/answers/")
                .content(objectMapper.writeValueAsString(personalityTestAnswers))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
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
}
