package ru.otus.igorr.lesson02.domain.question;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.igorr.lesson02.domain.question.Question;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    Question question;

    @BeforeEach
    void setUp() {
        question = new Question(1,"Q1");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addAnswer() {
    }

    @Test
    void getId() {
        assertEquals(1, question.getId());
    }

    @Test
    void setId() {
    }

    @Test
    void getText() {
    }

    @Test
    void getAnswerList() {
    }

    @Test
    void getCorrectAnswerTest1() {
        question.addAnswer(1,"Q1A1", false);
        question.addAnswer(2,"Q1A2", true);
        assertEquals(2, question.getCorrectAnswer());
    }

    @Test
    void getCorrectAnswerTest2() {
        question.addAnswer(1,"Q1A1", false);
        question.addAnswer(2,"Q1A2", false);
        assertEquals(0, question.getCorrectAnswer());
    }
}