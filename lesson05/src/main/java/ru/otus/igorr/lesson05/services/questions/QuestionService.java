package ru.otus.igorr.lesson05.services.questions;

import ru.otus.igorr.lesson05.domain.question.Question;

import java.util.List;

public interface QuestionService {
    void show(List<Question> questions);
    boolean ask(Question question);
}
