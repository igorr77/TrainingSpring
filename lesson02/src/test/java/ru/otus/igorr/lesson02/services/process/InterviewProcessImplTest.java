package ru.otus.igorr.lesson02.services.process;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.igorr.lesson02.dao.DataSource;
import ru.otus.igorr.lesson02.domain.question.Question;
import ru.otus.igorr.lesson02.domain.user.User;
import ru.otus.igorr.lesson02.services.message.MessageSources;
import ru.otus.igorr.lesson02.services.questions.QuestionService;
import ru.otus.igorr.lesson02.services.users.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InterviewProcessImplTest {

    private final String TOTAL_QUESTION = "5";

    @Mock
    UserService userService;
    @Mock
    QuestionService questionService;
    @Mock
    DataSource dataSource;
    @Mock
    MessageSources messageService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void InterviewProcessImplConstructorTest() {
        InterviewProcessImpl constructor = new InterviewProcessImpl(userService, questionService, dataSource, messageService, TOTAL_QUESTION);
        assertNotNull(constructor);
    }

    @Test
    void processTest() {

        User user = new User("User Name");
        List<Question> questionList = new ArrayList<>();
        Question question = new Question(1, "Q1");
        questionList.add(question);
        question.addAnswer(1, "Q1A1", false);
        question.addAnswer(2, "Q1A2", true);


        when(userService.getUser()).thenReturn(user);
        when(dataSource.prepareList()).thenReturn(questionList);
        when(questionService.ask(any(Question.class))).thenReturn(true);
        when(messageService.getMessage(any(String.class))).thenReturn("Message 1");
        //when(messageService.getMessage(any(String.class), new Object[] {any(Object.class)})).thenReturn("Message 2");


        InterviewProcess process = new InterviewProcessImpl(userService, questionService, dataSource, messageService, TOTAL_QUESTION);

        process.process();
    }
}