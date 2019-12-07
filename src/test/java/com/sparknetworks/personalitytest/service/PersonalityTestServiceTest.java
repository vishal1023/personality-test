package com.sparknetworks.personalitytest.service;

import com.sparknetworks.personalitytest.domain.PersonalityTestRepository;
import com.sparknetworks.personalitytest.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonalityTestServiceTest {

    @Mock
    private PersonalityTestRepository personalityTestRepository;

    private PersonalityTestService personalityTestService;

    @Before
    public void setUp() {
        personalityTestService = new PersonalityTestService(personalityTestRepository);
    }

    @Test
    public void shouldReturnListOfQuestions() {
        when(personalityTestRepository.getAllQuestions()).thenReturn(asList(
                new Question("What is your gender?", "hard_fact"),
                new Question("How often do your drink alcohol?", "lifestyle")
        ));

        List<Question> questions = personalityTestService.getAllQuestions();

        assertThat(questions.size(), is(2));
    }

    @Test
    public void shouldReturnListOfQuestionForGivenCategory() {
        String category = "introversion";
        when(personalityTestRepository.getQuestionsFor(category)).thenReturn(asList(
                new Question("I consciously take \"me time\"", "introversion"),
                new Question("Do you enjoy going on holiday by yourself?", "introversion")
        ));

        List<Question> questions = personalityTestService.getQuestionsFor("introversion");

        assertThat(questions.get(0).getCategory(), is("introversion"));
        assertThat(questions.get(1).getCategory(), is("introversion"));
    }
}