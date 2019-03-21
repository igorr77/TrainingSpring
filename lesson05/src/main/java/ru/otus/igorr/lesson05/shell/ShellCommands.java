package ru.otus.igorr.lesson05.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.igorr.lesson05.domain.question.Question;
import ru.otus.igorr.lesson05.domain.user.User;
import ru.otus.igorr.lesson05.services.process.InterviewProcess;
import ru.otus.igorr.lesson05.services.questions.QuestionService;
import ru.otus.igorr.lesson05.services.users.UserService;

@ShellComponent
public class ShellCommands {

    private boolean isLoginDone = false;
    private boolean isProcessDone = false;
    private boolean isResultDone = false;

    private InterviewProcess interviewProcess;
    private UserService userService;
    private QuestionService questionService;


    @Autowired
    public ShellCommands(InterviewProcess interviewProcess, UserService userService, QuestionService questionService) {
        this.interviewProcess = interviewProcess;
        this.userService = userService;
        this.questionService = questionService;
    }




    @ShellMethod(key = "login", value = "Login user")
    public void userLogin() {
        interviewProcess.login();
        isLoginDone = true;
    }

    @ShellMethod(key = "process", value = "Processing interview")
    @ShellMethodAvailability("loginAvailability")
    public void process() {
        interviewProcess.process();
        isProcessDone = true;
    }

    @ShellMethod(key = "result", value = "Show result")
    @ShellMethodAvailability("processAvailability")
    public void result(){
        interviewProcess.result();
    }


    public Availability loginAvailability(){
        return isLoginDone ? Availability.available() : Availability.unavailable("User no login");
    }

    public Availability processAvailability(){
        return isProcessDone ? Availability.available() : Availability.unavailable("No processing");
    }

}
