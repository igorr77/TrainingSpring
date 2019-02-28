package ru.otus.igorr.lesson01.services.questions;

import ru.otus.igorr.lesson01.domain.question.Question;
import ru.otus.igorr.lesson01.dao.ScannerService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {


    @Override
    public void show(List<Question> questions) {
        questions.stream().forEach(System.out::println);
    }

    @Override
    public boolean ask(Question question) {
        System.out.println("OptionQuestion: " + question.getText());
        question.getAnswerList().stream()
                .forEach(answer -> System.out.println(answer.showAnswer()));

        System.out.println("Input answer:");
        ScannerService scanner = ScannerService.getInstance();
        int userAnswer = scanner.nextInt();
        return question.getCorrectAnswer() == userAnswer;

    }
}
