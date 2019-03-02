package ru.otus.igorr.lesson02.services.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson02.dao.ScannerService;
import ru.otus.igorr.lesson02.domain.question.Question;
import ru.otus.igorr.lesson02.services.message.MessageSources;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private MessageSources messageService;

    @Autowired
    public QuestionServiceImpl(MessageSources messageService) {
        this.messageService = messageService;
    }


    @Override
    public void show(List<Question> questions) {
        questions.stream().forEach(System.out::println);
    }

    @Override
    public boolean ask(Question question) {

        String askQuestionText = messageService.getMessage("question.ask");
        String inputAnswerText = messageService.getMessage("question.input.answer");

        System.out.println(askQuestionText + question.getText());
        question.getAnswerList().stream()
                .forEach(answer -> System.out.println(answer.showAnswer()));

        System.out.println(inputAnswerText);
        ScannerService scanner = ScannerService.getInstance();
        int userAnswer = scanner.nextInt();
        return question.getCorrectAnswer() == userAnswer;

    }
}
