package com.sparknetworks.personalitytest.controller;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                new Question("How should your potential partner respond to this question?", "hard_fact", singleChoice),
                new Question("Do any children under the age of 18 live with you?", "lifestyle", singleChoice)
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
                new Question("How often do you smoke?", "lifestyle", singleChoice),
                new Question("What is your attitude towards drugs?", "lifestyle", singleChoice)
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
}
