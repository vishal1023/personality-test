package com.sparknetworks.personalitytest.controller;

import com.sparknetworks.personalitytest.domain.Question;
import com.sparknetworks.personalitytest.service.PersonalityTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
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

    @Test
    public void shouldGetListOfQuestion() throws Exception {
        when(this.personalityTestService.getAllQuestions()).thenReturn(asList(
                new Question("What is your gender?", "hard_fact"),
                new Question("How often do your drink alcohol?", "lifestyle")
        ));

        mockMvc.perform(get("/personality-test/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].questionText").value("What is your gender?"))
                .andExpect(jsonPath("$[0].category").value("hard_fact"))
                .andExpect(jsonPath("$[1].questionText").value("How often do your drink alcohol?"))
                .andExpect(jsonPath("$[1].category").value("lifestyle"));
    }

    @Test
    public void shouldGetListOfQuestionsForGivenCategory() throws Exception {
        when(this.personalityTestService.getQuestionsFor("lifestyle")).thenReturn(asList(
                new Question("How often do you smoke?", "lifestyle"),
                new Question("What is your attitude towards drugs?", "lifestyle")
        ));

        this.mockMvc.perform(get("/personality-test/questions/categories/lifestyle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value("lifestyle"));
    }
}
