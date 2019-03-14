package ru.otus.igorr.lesson04.services.questions;

import ru.otus.igorr.lesson04.domain.question.Question;

import java.util.List;

public interface QuestionService {
    void show(List<Question> questions);
    boolean ask(Question question);
}
