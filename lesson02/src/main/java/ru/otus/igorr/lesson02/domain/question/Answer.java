package ru.otus.igorr.lesson02.domain.question;

public class Answer {
    private int questionId;
    private int answerId;
    private String text;
    private boolean correct;

    public Answer(int questionId, int answerId, String text, boolean correct) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.text = text;
        this.correct = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getText() {
        return text;
    }


    public boolean isCorrect() {
        return correct;
    }

    public String showAnswer() {
        return String.format("%s. %s", answerId, text);
    }
}
