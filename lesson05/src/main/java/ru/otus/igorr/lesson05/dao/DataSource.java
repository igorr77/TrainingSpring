package ru.otus.igorr.lesson05.dao;

import ru.otus.igorr.lesson05.domain.question.Question;

import java.util.List;

public interface DataSource {
    List<Question> prepareList();
}
