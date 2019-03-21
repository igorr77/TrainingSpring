package ru.otus.igorr.lesson05.services.process;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.igorr.lesson05.config.TestingProps;
import ru.otus.igorr.lesson05.dao.DataSource;
import ru.otus.igorr.lesson05.dao.ScannerService;
import ru.otus.igorr.lesson05.domain.question.Question;
import ru.otus.igorr.lesson05.domain.user.User;
import ru.otus.igorr.lesson05.services.message.MessageSources;
import ru.otus.igorr.lesson05.services.process.InterviewProcess;
import ru.otus.igorr.lesson05.services.process.InterviewProcessImpl;
import ru.otus.igorr.lesson05.services.questions.QuestionService;
import ru.otus.igorr.lesson05.services.users.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class InterviewProcessImplTest {

    @MockBean
    UserService userService;
    @MockBean
    QuestionService questionService;
    @MockBean
    DataSource dataSource;
    @MockBean
    MessageSources messageService;
    @MockBean
    TestingProps props;
    @MockBean
    ScannerService scannerService;

    @Autowired
    InterviewProcess process;

    @Test
    void InterviewProcessImplConstructorTest() {
        when(props.getTotalCountQuestion()).thenReturn(5);
        InterviewProcessImpl constructor = new InterviewProcessImpl(userService, questionService, dataSource, messageService, props);
        assertNotNull(constructor);
    }

    @Test
    void loginTest(){
        User user = new User("User User");
        when(messageService.getMessage(any(String.class))).thenReturn("input.name");
        when(userService.getUser()).thenReturn(user);
        assertNotNull(process.login());
    }

    @ParameterizedTest
    @ValueSource(ints = 5)
    void processTest(int param) {

        List<Question> questionList = new ArrayList<>();
        Question question = new Question(1, "Q1");
        questionList.add(question);
        question.addAnswer(1, "Q1A1", false);
        question.addAnswer(2, "Q1A2", true);

        when(dataSource.prepareList()).thenReturn(questionList);
        when(questionService.ask(any(Question.class))).thenReturn(true);
        when(messageService.getMessage(any(String.class))).thenReturn("Message 1");
        when(props.getTotalCountQuestion()).thenReturn(param);

        process.process();
        assertEquals( 1.0/param, process.result());
    }
}