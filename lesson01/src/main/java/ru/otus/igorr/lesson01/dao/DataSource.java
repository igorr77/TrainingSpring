package ru.otus.igorr.lesson01.dao;

import ru.otus.igorr.lesson01.domain.question.Question;

import java.util.List;

public interface DataSource {
    List<Question> prepareList();

}
