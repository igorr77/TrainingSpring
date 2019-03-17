package ru.otus.igorr.lesson05.services.questions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.igorr.lesson05.dao.ScannerService;
import ru.otus.igorr.lesson05.domain.question.Question;
import ru.otus.igorr.lesson05.services.message.MessageSources;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuestionServiceImplTest {

    private QuestionServiceImpl service;
    private List<Question> questionList;
    private Question question;

    @MockBean
    MessageSources messageService;
    @MockBean
    ScannerService scannerService;

    @BeforeEach
    void setUp() {
        initQuestionList();
        service = new QuestionServiceImpl(messageService, scannerService);
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
    @ValueSource(ints = {2})
    void askTrueTest(int param) {
        initQuestion();

        when(messageService.getMessage(any(String.class))).thenReturn("Question:");
        when(scannerService.nextInt()).thenReturn(param);
        assertTrue(service.ask(question));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void askFalseTest(int param) {

        initQuestion();

        when(messageService.getMessage(any(String.class))).thenReturn("Question:");
        when(scannerService.nextInt()).thenReturn(param);

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