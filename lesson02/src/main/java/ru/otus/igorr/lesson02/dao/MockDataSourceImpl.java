package ru.otus.igorr.lesson02.dao;

import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson02.domain.question.Question;

import java.util.Collections;
import java.util.List;

@Service("mockDataSource")
public class MockDataSourceImpl implements DataSource {

    @Override
    public List<Question> prepareList() {
        return Collections.emptyList();
    }
}

