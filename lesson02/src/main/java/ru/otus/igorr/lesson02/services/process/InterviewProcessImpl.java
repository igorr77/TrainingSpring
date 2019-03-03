package ru.otus.igorr.lesson02.services.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson02.dao.DataSource;
import ru.otus.igorr.lesson02.domain.question.Question;
import ru.otus.igorr.lesson02.domain.user.User;
import ru.otus.igorr.lesson02.services.message.MessageSources;
import ru.otus.igorr.lesson02.services.questions.QuestionService;
import ru.otus.igorr.lesson02.services.users.UserService;

import java.util.List;

@Service
public class InterviewProcessImpl implements InterviewProcess {

    private final UserService userService;
    private final QuestionService questionService;
    private final DataSource dataSource;
    private final MessageSources messageServices;
    private final int totalQuestion;

    @Autowired
    public InterviewProcessImpl(final UserService userService,
                                final QuestionService questionService,
                                @Qualifier("fileDataSource") final DataSource dataSource,
                                final MessageSources messageService,
                                @Value("${question.total.count}") final String totalQuestion
    ) {
        this.userService = userService;
        this.questionService = questionService;
        this.dataSource = dataSource;
        this.messageServices = messageService;
        this.totalQuestion = Integer.valueOf(totalQuestion);
    }

    @Override
    public void process() {
        String helloText = messageServices.getMessage("input.hello");
        String resultText = messageServices.getMessage("out.result");

        User user = userService.getUser();
        System.out.println(String.format(helloText, user.getName()));

        List<Question> questions = dataSource.prepareList();
        Long successAnswer = questions.stream()
                .limit(totalQuestion)
                .map(question -> questionService.ask(question))
                .filter(Boolean::booleanValue)
                .count();
        Double result = Double.valueOf(successAnswer) / Double.valueOf(totalQuestion);

        System.out.println(String.format(resultText, result));
    }


}
