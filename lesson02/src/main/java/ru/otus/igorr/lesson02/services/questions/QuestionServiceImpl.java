package ru.otus.igorr.lesson02.services.questions;

import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson02.domain.question.Question;
import ru.otus.igorr.lesson02.dao.ScannerService;

import java.util.List;

@Service
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
