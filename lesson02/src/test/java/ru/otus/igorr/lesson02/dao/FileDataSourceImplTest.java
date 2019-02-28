package ru.otus.igorr.lesson02.dao;

import org.hamcrest.core.AnyOf;
import ru.otus.igorr.lesson02.domain.question.Item;
import ru.otus.igorr.lesson02.dao.FileDataSourceImpl;
import ru.otus.igorr.lesson02.domain.question.ItemType;
import ru.otus.igorr.lesson02.domain.question.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileDataSourceImplTest {

    @Mock
    FileDataSourceImpl dataSource;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readDataTest() throws IOException {
        when(dataSource.readData(any(String.class))).thenCallRealMethod();
        dataSource.readData("data/questions.csv");

    }


    @Test
    void prepareListTest() throws IOException {
        List<Item> result = new ArrayList();
        result.add(new Item(ItemType.Q, 0, 1, "Q1", false));
        result.add(new Item(ItemType.A, 1, 1, "Q1A1", false));
        result.add(new Item(ItemType.A, 1, 2, "Q1A2", true));

        when(dataSource.readData(null)).thenReturn(result);
        when(dataSource.prepareList()).thenCallRealMethod();
        List<Question> questionList = dataSource.prepareList();

        assertAll("qqq",
                () -> assertNotEquals(0, questionList.size()),
                () -> assertEquals("Q1", questionList.get(0).getText()),
                () -> assertEquals("Q1A1", questionList.get(0).getAnswerList().get(0).getText())
        );
    }

    @Test
    void prepareListExceptionTest() throws IOException {

        when(dataSource.readData(any(String.class))).thenReturn(null);
        when(dataSource.prepareList()).thenCallRealMethod();

        Exception exception = assertThrows(Exception.class, () -> dataSource.prepareList());

    }
}
