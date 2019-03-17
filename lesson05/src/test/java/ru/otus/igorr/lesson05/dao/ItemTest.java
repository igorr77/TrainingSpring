package ru.otus.igorr.lesson05.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.igorr.lesson05.domain.question.Item;
import ru.otus.igorr.lesson05.domain.question.ItemType;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private final String[] qRow = {"Q", " 0", " 1  ", "   Question Q1"};
    private final String[] aRow = {"A ", " 1", "  1", "  Answer Q1A1 ", "true"};


    @BeforeEach
    void setUp() {
    }

    @Test
    void parseQTest() {
        Item qa = new Item(qRow);
        assertAll(
                () -> assertEquals(ItemType.Q, qa.getType()),
                () -> assertEquals(0, qa.getParentId()),
                () -> assertEquals(1, qa.getId()),
                () -> assertTrue(qRow[3].contains(qa.getText()), "NO"),
                () -> assertFalse(qa.isCorrect())

        );
    }

    @Test
    void parseATest() {
        Item qa = new Item(aRow);
        assertAll(
                () -> assertEquals(ItemType.A, qa.getType()),
                () -> assertEquals(1, qa.getParentId()),
                () -> assertEquals(1, qa.getId()),
                () -> assertTrue(aRow[3].contains(qa.getText()), "NO"),
                () -> assertTrue(qa.isCorrect())
        );

    }

    @Test
    void getType() {
    }

    @Test
    void getParentId() {
    }

    @Test
    void getId() {
    }

    @Test
    void getText() {
    }

    @Test
    void isCorrect() {
    }
}