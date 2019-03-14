package ru.otus.igorr.lesson04.dao;

import ru.otus.igorr.lesson04.domain.question.Question;

import java.util.List;

public interface DataSource {
    List<Question> prepareList();
}
