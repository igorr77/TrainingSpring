package ru.otus.igorr.lesson04.services.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson04.dao.ScannerService;
import ru.otus.igorr.lesson04.domain.question.Question;
import ru.otus.igorr.lesson04.services.message.MessageSources;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final MessageSources messageService;
    private final ScannerService scanner;

    @Autowired
    public QuestionServiceImpl(MessageSources messageService, ScannerService scannerService) {
        this.messageService = messageService;
        this.scanner = scannerService;
    }


    @Override
    public void show(List<Question> questions) {
        questions.forEach(System.out::println);
    }

    @Override
    public boolean ask(Question question) {

        String askQuestionText = messageService.getMessage("question.ask");
        String inputAnswerText = messageService.getMessage("question.input.answer");

        System.out.println(askQuestionText + question.getText());
        question.getAnswerList().forEach(answer -> System.out.println(answer.showAnswer()));

        System.out.println(inputAnswerText);
        int userAnswer = scanner.nextInt();
        return question.getCorrectAnswer().getAnswerId() == userAnswer;

    }
}
