package com.sparknetworks.personalitytest.service;

import com.sparknetworks.personalitytest.domain.PersonalityTestRepository;
import com.sparknetworks.personalitytest.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.sparknetworks.personalitytest.domain.Category.HARD_FACT;
import static com.sparknetworks.personalitytest.domain.Category.LIFESTYLE;
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
        when(personalityTestRepository.getAllQuestions()).thenReturn(Arrays.asList(
                new Question("What is your gender?", HARD_FACT),
                new Question("How often do your drink alcohol?", LIFESTYLE)
        ));

        List<Question> questions = personalityTestService.getAllQuestions();

        assertThat(questions.size(), is(2));
    }
}