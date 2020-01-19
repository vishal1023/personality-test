package com.sparknetworks.personalitytest.service;

import com.sparknetworks.personalitytest.domain.answer.*;
import com.sparknetworks.personalitytest.domain.question.*;
import com.sparknetworks.personalitytest.exception.NotFoundException;
import com.sparknetworks.personalitytest.repository.PersonalityTestRepository;
import com.sparknetworks.personalitytest.repository.mongodb.PersonalityTestKey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonalityTestServiceTest {

    private static final String INTROVERSION_CATEGORY = "introversion";
    @Mock
    private PersonalityTestRepository personalityTestRepository;

    private PersonalityTestService personalityTestService;
    private QuestionType singleChoice;

    @Before
    public void setUp() {
        personalityTestService = new PersonalityTestService(personalityTestRepository);
        singleChoice = new SingleChoiceQuestion(asList("yes", "sometimes", "no"));
    }

    @Test
    public void shouldReturnListOfQuestions() {
        when(personalityTestRepository.getAllQuestions()).thenReturn(asList(
                new Question("Do any children under the age of 18 live with you?", "hard_fact", singleChoice),
                new Question("How should your potential partner respond to this question?", "lifestyle", singleChoice)
        ));

        PersonalityTestQuestions personalityTestQuestions = personalityTestService.getAllQuestions();

        assertNotNull(personalityTestQuestions);
        assertThat(personalityTestQuestions.getQuestions().size(), is(2));
    }

    @Test
    public void shouldReturnListOfQuestionForGivenCategory() {
        when(personalityTestRepository.getQuestionsFor(INTROVERSION_CATEGORY)).thenReturn(asList(
                new Question("Do any children under the age of 18 live with you?", INTROVERSION_CATEGORY, singleChoice),
                new Question("Do you enjoy going on holiday by yourself?", INTROVERSION_CATEGORY, singleChoice)
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

    @Test
    public void shouldGetDifferentTypesOfQuestions() {
        QuestionType rangeType = new NumberRangeQuestion(new Range<>(18, 40));
        List<String> initialOptions = asList("yes", "sometimes", "no");
        Question conditionalQuestion = new Question("What age should your potential partner be?", INTROVERSION_CATEGORY, rangeType);
        Condition condition =
                new Condition(new Predicate(Arrays.asList("${selection}", "very important")), conditionalQuestion);
        QuestionType singleChoiceConditional = new SingleChoiceConditionalQuestion(initialOptions, condition);

        when(personalityTestRepository.getAllQuestions()).thenReturn(asList(
                new Question("How should your potential partner respond to this question?", INTROVERSION_CATEGORY, singleChoice),
                new Question("What age should your potential partner be?", INTROVERSION_CATEGORY, rangeType),
                new Question("What age should your potential partner be?", INTROVERSION_CATEGORY, singleChoiceConditional)
        ));

        PersonalityTestQuestions personalityTestServiceAllQuestions = personalityTestService.getAllQuestions();
        List<Question> questions = personalityTestServiceAllQuestions.getQuestions();

        SingleChoiceQuestion singleChoiceQuestion = (SingleChoiceQuestion) questions.get(0).getQuestionType();
        NumberRangeQuestion numberRangeQuestion = (NumberRangeQuestion) questions.get(1).getQuestionType();
        SingleChoiceConditionalQuestion singleChoiceConditionalQuestion = (SingleChoiceConditionalQuestion) questions.get(2).getQuestionType();

        assertNotNull(singleChoiceQuestion.getOptions());
        assertNotNull(numberRangeQuestion.getRange());
        assertNotNull(singleChoiceConditionalQuestion.getOptions());
        assertNotNull(singleChoiceConditionalQuestion.getCondition());
//        assertNotNull(singleChoiceConditionalQuestion.getConditionalQuestion());
    }

    @Test
    public void shouldSaveTestAnswer() {
        AnswerType answer = new SingleChoiceAnswer("SingleChoice", "Yes");
        TestAnswers personalityTestAnswers = new PersonalityTestAnswers(new PersonalityTestKey("test1", "100"),
                singletonList(new Answer("a1", answer)));

        personalityTestService.saveTestAnswers(personalityTestAnswers);

        verify(personalityTestRepository, times(1))
                .saveTestAnswer(personalityTestAnswers);
    }

    @Test
    public void shouldDeleteQuestionForGiveQuestionId() {
        String questionId = "someQuestionId";
        personalityTestService.deleteQuestion(questionId);

        verify(personalityTestRepository, times(1))
                .deleteQuestion(questionId);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowRuntimeExceptionIfQuestionIdNotPresent() {
        String questionId = "someQuestionId";
        doThrow(new RuntimeException())
                .when(personalityTestRepository)
                .deleteQuestion(questionId);

        personalityTestService.deleteQuestion(questionId);
    }
}