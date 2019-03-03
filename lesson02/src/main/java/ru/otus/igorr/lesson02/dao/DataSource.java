package ru.otus.igorr.lesson02.dao;

import ru.otus.igorr.lesson02.domain.question.Question;

import java.util.List;

public interface DataSource {
    List<Question> prepareList();

}
