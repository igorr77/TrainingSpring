package ru.otus.igorr.lesson05.domain.question;

public class Answer {
    private final int questionId;
    private final int answerId;
    private final String text;
    private final boolean correct;

    public Answer(int questionId, int answerId, String text, boolean correct) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.text = text;
        this.correct = correct;
    }

    public Answer(){
        this.questionId = -1;
        this.answerId = -1;
        this.text = "empty";
        this.correct = true;
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

    @Override
    public String toString() {
        return "Answer{" +
                "questionId=" + questionId +
                ", answerId=" + answerId +
                ", text='" + text + '\'' +
                ", correct=" + correct +
                '}';
    }
}
