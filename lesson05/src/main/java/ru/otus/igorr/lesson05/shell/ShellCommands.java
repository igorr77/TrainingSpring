package ru.otus.igorr.lesson05.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.igorr.lesson05.domain.question.Question;
import ru.otus.igorr.lesson05.domain.user.User;
import ru.otus.igorr.lesson05.services.process.InterviewProcess;
import ru.otus.igorr.lesson05.services.questions.QuestionService;
import ru.otus.igorr.lesson05.services.users.UserService;

@ShellComponent
public class ShellCommands {

    private InterviewProcess interviewProcess;
    private UserService userService;
    private QuestionService questionService;


    @Autowired
    public ShellCommands(InterviewProcess interviewProcess, UserService userService, QuestionService questionService) {
        this.interviewProcess = interviewProcess;
        this.userService = userService;
        this.questionService = questionService;
    }


    @ShellMethod("Start app")
    public void runApp() {
        interviewProcess.process();
    }

    @ShellMethod("Test users service")
    public void testUsers() {
        User user = userService.getUser();
        System.out.println(user);
        System.out.println("User.getName(): " + user.getName());

    }

    @ShellMethod(value = "Test question service", key = "askTrueTest")
    public void testQuestion1(
            @ShellOption(defaultValue = "Question 1") String text
    ) {
        Question question = initQuestion(text);
        System.out.println(question);

        System.out.println("Input correct answer:");
        assertTrue(questionService.ask(question));
    }

    @ShellMethod(value = "Test question service", key = "askFalseTest")
    public void testQuestion2(
            @ShellOption(defaultValue = "Question 2") String text
    ) {
        Question question = initQuestion(text);
        System.out.println(question);

        System.out.println("Input incorrect answer:");
        assertFalse(questionService.ask(question));
    }


    private Question initQuestion(String text) {
        Question question = new Question(1, text);
        question.addAnswer(1, "Answer 1", false);
        question.addAnswer(2, "Answer 2", true);
        question.addAnswer(3, "Answer 3", false);
        return question;
    }

    private void assertTrue(boolean actual) {
        System.out.println(actual ? "success" : "fail");
    }

    private void assertFalse(boolean actual) {
        System.out.println(!actual ? "success" : "fail");
    }

    private void assertNotNull(Object actual) {
        String result = actual != null ? "success" : "fail";
        System.out.println(result);
    }

}
