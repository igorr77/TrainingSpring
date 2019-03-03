package ru.otus.igorr.lesson02.domain.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Question {
    private int id;
    private String text;
    private List<Answer> answerList = new ArrayList<>();

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

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public int getCorrectAnswer() {

        Optional<Answer> answer = answerList.stream()
                .filter(Answer::isCorrect)
                .findFirst();
        if (answer.isPresent()) {
            return answer.get().getAnswerId();
        } else {
            return 0;
        }

    }
}