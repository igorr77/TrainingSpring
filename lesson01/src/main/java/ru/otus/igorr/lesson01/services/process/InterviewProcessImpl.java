package ru.otus.igorr.lesson01.services.process;

import ru.otus.igorr.lesson01.dao.DataSource;
import ru.otus.igorr.lesson01.domain.question.Question;
import ru.otus.igorr.lesson01.services.questions.QuestionService;
import ru.otus.igorr.lesson01.domain.user.User;
import ru.otus.igorr.lesson01.services.users.UserService;

import java.util.List;

public class InterviewProcessImpl implements InterviewProcess {

    private static final int TOTAL_QUESTION = 5;
    private final UserService userService;
    private final QuestionService questionService;
    private final DataSource dataSource;

    public InterviewProcessImpl(final UserService userService, final QuestionService questionService, final DataSource dataSource) {
        this.userService = userService;
        this.questionService = questionService;
        this.dataSource = dataSource;
    }


    public void process() {
        User user = userService.getUser();
        System.out.println(String.format("Hello, %s!", user.getName()));
        List<Question> questions = dataSource.prepareList();
        Long successAnswer = questions.stream()
                                      .limit(TOTAL_QUESTION)
                                      .map(question -> questionService.ask(question))
                                      .filter(Boolean::booleanValue)
                                      .count();
        Double result = Double.valueOf(successAnswer) / Double.valueOf(TOTAL_QUESTION);
        System.out.println(String.format("Result %s", result));
    }


}
