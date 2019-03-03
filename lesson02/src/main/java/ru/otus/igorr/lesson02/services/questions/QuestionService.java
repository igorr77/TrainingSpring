package ru.otus.igorr.lesson02.services.questions;

import ru.otus.igorr.lesson02.domain.question.Question;

import java.util.List;

public interface QuestionService {
    void show(List<Question> questions);
    boolean ask(Question question);
}
