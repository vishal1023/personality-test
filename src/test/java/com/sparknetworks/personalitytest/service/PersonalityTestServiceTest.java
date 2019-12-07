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
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonalityTestServiceTest {

    private static final String INTROVERSION_CATEGORY = "introversion";
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
        when(personalityTestRepository.getQuestionsFor(INTROVERSION_CATEGORY)).thenReturn(asList(
                new Question("I consciously take \"me time\"", INTROVERSION_CATEGORY),
                new Question("Do you enjoy going on holiday by yourself?", INTROVERSION_CATEGORY)
        ));

        List<Question> questions = personalityTestService.getQuestionsFor(INTROVERSION_CATEGORY);

        assertThat(questions.get(0).getCategory(), is(INTROVERSION_CATEGORY));
        assertThat(questions.get(1).getCategory(), is(INTROVERSION_CATEGORY));
    }

    @Test
    public void shouldReturnEmptyListIfQuestionNotFoundGivenCategory() {
        when(personalityTestRepository.getQuestionsFor("unknown-category")).thenReturn(emptyList());

        List<Question> questions = personalityTestService.getQuestionsFor("unknown-category");

        assertThat(questions.size(), is(0));
    }
}