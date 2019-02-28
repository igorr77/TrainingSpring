package ru.otus.igorr.lesson01.dao;

import ru.otus.igorr.lesson01.domain.question.Question;

import java.util.Collections;
import java.util.List;

public class MockDataSourceImpl implements DataSource {

    @Override
    public List<Question> prepareList() {
        return Collections.emptyList();
    }
}

