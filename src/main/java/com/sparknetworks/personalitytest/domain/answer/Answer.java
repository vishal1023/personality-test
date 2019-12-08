package com.sparknetworks.personalitytest.domain.answer;

import java.util.concurrent.atomic.AtomicLong;

public class Answer {

    private final static AtomicLong counter = new AtomicLong(0);
    private long id;
    private String questionId;
    private AnswerType answerType;

    public Answer() {
    }

    public Answer(String questionId, AnswerType answerType) {
        this.id = counter.incrementAndGet();
        this.questionId = questionId;
        this.answerType = answerType;
    }

    public String getQuestionId() {
        return questionId;
    }

    public long getId() {
        return id;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId='" + questionId + '\'' +
                ", answerType=" + answerType +
                '}';
    }
}
