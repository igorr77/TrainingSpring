package ru.otus.igorr.lesson05.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.igorr.lesson05.config.DataSourceProps;
import ru.otus.igorr.lesson05.domain.question.Item;
import ru.otus.igorr.lesson05.domain.question.ItemType;
import ru.otus.igorr.lesson05.domain.question.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FileDataSourceImplTest {

    @Autowired
    DataSource dataSource;
    @MockBean
    private DataSourceProps props;


    @Test
    void prepareListTest() {
        List<Item> result = new ArrayList();
        result.add(new Item(ItemType.Q, 0, 1, "Q1", false));
        result.add(new Item(ItemType.A, 1, 1, "Q1A1", false));
        result.add(new Item(ItemType.A, 1, 2, "Q1A2", true));

        when(props.getFile()).thenReturn("data/questions.csv");
        List<Question> questionList = dataSource.prepareList();

        assertAll("qqq",
                  () -> assertNotEquals(0, questionList.size()),
                  () -> assertEquals("question 1", questionList.get(0).getText()),
                  () -> assertEquals("possibleAnswer 1", questionList.get(0).getAnswerList().get(0).getText())
        );
    }

}
