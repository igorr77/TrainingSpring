package ru.otus.igorr.lesson05.services.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson05.config.TestingProps;
import ru.otus.igorr.lesson05.dao.DataSource;
import ru.otus.igorr.lesson05.domain.question.Question;
import ru.otus.igorr.lesson05.domain.user.User;
import ru.otus.igorr.lesson05.services.message.MessageSources;
import ru.otus.igorr.lesson05.services.questions.QuestionService;
import ru.otus.igorr.lesson05.services.users.UserService;

import java.util.List;

@Service
@EnableConfigurationProperties(TestingProps.class)
public class InterviewProcessImpl implements InterviewProcess {

    private static final Logger LOG = LoggerFactory.getLogger(InterviewProcessImpl.class);

    private final UserService userService;
    private final QuestionService questionService;
    private final DataSource dataSource;
    private final MessageSources messageServices;
    private final TestingProps props;

    @Autowired
    public InterviewProcessImpl(final UserService userService,
                                final QuestionService questionService,
                                @Qualifier("fileDataSource") final DataSource dataSource,
                                final MessageSources messageService,
                                TestingProps props
    ) {
        this.userService = userService;
        this.questionService = questionService;
        this.dataSource = dataSource;
        this.messageServices = messageService;
        this.props = props;

        LOG.debug("TotalCountQuestion: {}", this.props.getTotalCountQuestion());
    }

    @Override
    public void process() {
        String helloText = messageServices.getMessage("input.hello");
        String resultText = messageServices.getMessage("out.result");

        User user = userService.getUser();
        System.out.println(String.format(helloText, user.getName()));

        List<Question> questions = dataSource.prepareList();
        Long successAnswer = questions.stream()
                .limit(props.getTotalCountQuestion())
                .map(question -> questionService.ask(question))
                .filter(Boolean::booleanValue)
                .count();
        Double result = Double.valueOf(successAnswer) / Double.valueOf(props.getTotalCountQuestion());

        System.out.println(String.format(resultText, result));
    }


}
