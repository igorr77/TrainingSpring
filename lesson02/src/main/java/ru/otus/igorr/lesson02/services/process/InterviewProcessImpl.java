package ru.otus.igorr.lesson02.services.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson02.dao.DataSource;
import ru.otus.igorr.lesson02.domain.question.Question;
import ru.otus.igorr.lesson02.services.questions.QuestionService;
import ru.otus.igorr.lesson02.domain.user.User;
import ru.otus.igorr.lesson02.services.users.UserService;

import java.util.List;

@Service
public class InterviewProcessImpl implements InterviewProcess {

    private static final int TOTAL_QUESTION = 5;
    private final UserService userService;
    private final QuestionService questionService;
    private final DataSource dataSource;

    @Autowired
    public InterviewProcessImpl(final UserService userService,
                                final QuestionService questionService,
                                @Qualifier("fileDataSource") final DataSource dataSource) {
        this.userService = userService;
        this.questionService = questionService;
        this.dataSource = dataSource;
    }

    @Override
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
