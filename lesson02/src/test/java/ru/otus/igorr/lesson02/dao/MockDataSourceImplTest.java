package ru.otus.igorr.lesson02.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.igorr.lesson02.dao.DataSource;
import ru.otus.igorr.lesson02.dao.MockDataSourceImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MockDataSourceImplTest {

    DataSource mockDataSource;

    @BeforeEach
    void setUp() {
        mockDataSource = new MockDataSourceImpl();
    }

    @Test
    void prepareListTest() {
        assertEquals(Collections.EMPTY_LIST, mockDataSource.prepareList());
    }
}