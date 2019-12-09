package com.sparknetworks.personalitytest.repository.mongodb;

import com.sparknetworks.personalitytest.domain.answer.TestAnswers;
import com.sparknetworks.personalitytest.domain.question.PersonalityTestQuestions;
import com.sparknetworks.personalitytest.domain.question.Question;
import com.sparknetworks.personalitytest.repository.PersonalityTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonalityTestMongoDBRepository implements PersonalityTestRepository {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public PersonalityTestMongoDBRepository(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsFor(String category) {
        List<Question> allQuestions = questionRepository.findAll();
        return allQuestions
                .stream()
                .filter(q -> q.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public void saveTestAnswer(TestAnswers testAnswers) {
        answerRepository.insert(testAnswers);
    }

}
