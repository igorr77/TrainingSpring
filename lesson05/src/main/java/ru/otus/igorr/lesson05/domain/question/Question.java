package ru.otus.igorr.lesson05.domain.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Question {
    private final int id;
    private final String text;
    private final List<Answer> answerList = new ArrayList<>();

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public void addAnswer(int id, String text, boolean correct) {
        answerList.add(new Answer(this.id, id, text, correct));
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public Answer getCorrectAnswer() {

        Optional<Answer> answer = answerList.stream()
                .filter(Answer::isCorrect)
                .findFirst();
        return answer.orElse(new Answer());
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answerList=" + answerList +
                '}';
    }
}