package ru.otus.igorr.lesson01.services.process;

import ru.otus.igorr.lesson01.dao.DataSource;
import ru.otus.igorr.lesson01.domain.question.Question;
import ru.otus.igorr.lesson01.services.questions.QuestionService;
import ru.otus.igorr.lesson01.domain.user.User;
import ru.otus.igorr.lesson01.services.users.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InterviewProcessImplTest {

    @Mock
    UserService userService;
    @Mock
    QuestionService questionService;
    @Mock
    DataSource dataSource;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void InterviewProcessImplConstructorTest() {
        InterviewProcessImpl constructor = new InterviewProcessImpl(userService, questionService, dataSource);
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
        when(questionService.ask( any(Question.class) )).thenReturn(true);


        InterviewProcess process = new InterviewProcessImpl(userService, questionService, dataSource);

        process.process();
    }
}