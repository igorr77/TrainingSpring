package ru.otus.igorr.lesson05.services.process;

import ru.otus.igorr.lesson05.domain.user.User;

public interface InterviewProcess {
    User login();
    void process();
    double result();
}
