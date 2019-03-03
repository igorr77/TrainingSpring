package ru.otus.igorr.lesson02.services.questions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.igorr.lesson02.dao.ScannerService;
import ru.otus.igorr.lesson02.domain.question.Question;
import ru.otus.igorr.lesson02.services.message.MessageSources;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    private QuestionServiceImpl service;
    private List<Question> questionList;
    private Question question;

    @Mock
    MessageSources messageService;

    @BeforeEach
    void setUp() {
        initQuestionList();
        service = new QuestionServiceImpl(messageService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void read() {
    }

    @Test
    void showTest() {
        service.show(questionList);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2"})
    void askTrueTest(String param) throws IOException {
        initQuestion();

        ByteArrayInputStream input = new ByteArrayInputStream(param.getBytes());
        System.setIn(input);

        ScannerService.getInstance().setInputStream(System.in);

        when(messageService.getMessage(any(String.class))).thenReturn("Question:");
        assertTrue(service.ask(question));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "3"})
    void askFalseTest(String param) {

        initQuestion();

        ByteArrayInputStream input = new ByteArrayInputStream(param.getBytes());
        System.setIn(input);

        ScannerService.getInstance().setInputStream(System.in);

        when(messageService.getMessage(any(String.class))).thenReturn("Question:");
        assertFalse(service.ask(question));
    }

    private void initQuestion() {
        question = new Question(1, "OptionQuestion 1");
        question.addAnswer(1, "answer 1", false);
        question.addAnswer(2, "answer 2", true);
        question.addAnswer(3, "answer 3", false);

    }

    private void initQuestionList() {
        questionList = new ArrayList<>();
        questionList.add(new Question(1, "OptionQuestion 1"));
        questionList.add(new Question(2, "OptionQuestion 2"));
        questionList.add(new Question(3, "OptionQuestion 3"));
    }


}