package ru.otus.igorr.lesson05.domain.question;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.igorr.lesson05.domain.question.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(1,"Q1");
    }

    @Test
    void getId() {
        assertEquals(1, question.getId());
    }


    @Test
    void getCorrectAnswerTest1() {
        question.addAnswer(1,"Q1A1", false);
        question.addAnswer(2,"Q1A2", true);
        assertEquals(2, question.getCorrectAnswer().getAnswerId());
    }

    @Test
    void getCorrectAnswerTest2() {
        final int FAIL_ANSWER = -1;

        question.addAnswer(1,"Q1A1", false);
        question.addAnswer(2,"Q1A2", false);
        assertEquals(FAIL_ANSWER, question.getCorrectAnswer().getAnswerId());
    }
}