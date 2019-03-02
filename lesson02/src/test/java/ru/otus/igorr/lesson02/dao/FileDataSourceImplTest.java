package ru.otus.igorr.lesson02.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.igorr.lesson02.domain.question.Item;
import ru.otus.igorr.lesson02.domain.question.ItemType;
import ru.otus.igorr.lesson02.domain.question.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FileDataSourceImplTest {

    FileDataSourceImpl dataSource;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void prepareListTest() throws IOException {
        List<Item> result = new ArrayList();
        result.add(new Item(ItemType.Q, 0, 1, "Q1", false));
        result.add(new Item(ItemType.A, 1, 1, "Q1A1", false));
        result.add(new Item(ItemType.A, 1, 2, "Q1A2", true));

        dataSource = new FileDataSourceImpl("data/questions.csv");
        List<Question> questionList = dataSource.prepareList();

        assertAll("qqq",
                () -> assertNotEquals(0, questionList.size()),
                () -> assertEquals("question 1", questionList.get(0).getText()),
                () -> assertEquals("possibleAnswer 1", questionList.get(0).getAnswerList().get(0).getText())
        );
    }

}
