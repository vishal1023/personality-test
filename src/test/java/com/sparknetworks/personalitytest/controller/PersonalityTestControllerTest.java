package com.sparknetworks.personalitytest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.personalitytest.domain.answer.*;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.domain.question.QuestionType;
import com.sparknetworks.personalitytest.domain.question.SingleChoiceQuestion;
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
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
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
        when(this.personalityTestService.getAllQuestions()).thenReturn(asList(
                new Question("questionId", "How should your potential partner respond to this question?", "hard_fact", singleChoice),
                new Question("questionId", "Do any children under the age of 18 live with you?", "lifestyle", singleChoice)
        ));

        mockMvc.perform(get("/personality-test/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].questionText").value("How should your potential partner respond to this question?"))
                .andExpect(jsonPath("$[0].category").value("hard_fact"))
                .andExpect(jsonPath("$[1].questionText").value("Do any children under the age of 18 live with you?"))
                .andExpect(jsonPath("$[1].category").value("lifestyle"));
    }

    @Test
    public void shouldGetEmptyListOfQuestionIfNoQuestionsFound() throws Exception {
        when(this.personalityTestService.getAllQuestions()).thenReturn(emptyList());

        mockMvc.perform(get("/personality-test/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetListOfQuestionsForGivenCategory() throws Exception {
        when(this.personalityTestService.getQuestionsFor("lifestyle")).thenReturn(asList(
                new Question("questionId", "How often do you smoke?", "lifestyle", singleChoice),
                new Question("questionId", "What is your attitude towards drugs?", "lifestyle", singleChoice)
        ));

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
        TestAnswers personalityTestAnswers = new PersonalityTestAnswers("test1", "100", getAnswers());

        this.mockMvc.perform(post("/personality-test/answers/")
                .content(objectMapper.writeValueAsString(personalityTestAnswers))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
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
