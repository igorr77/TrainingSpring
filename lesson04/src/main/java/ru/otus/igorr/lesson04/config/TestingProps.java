package ru.otus.igorr.lesson04.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "testing")
public class TestingProps {
    private int totalCountQuestion;

    public int getTotalCountQuestion() {
        return totalCountQuestion;
    }

    public void setTotalCountQuestion(int totalCountQuestion) {
        this.totalCountQuestion = totalCountQuestion;
    }
}
