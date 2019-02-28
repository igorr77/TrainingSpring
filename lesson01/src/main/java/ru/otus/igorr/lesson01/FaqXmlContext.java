package ru.otus.igorr.lesson01;

import ru.otus.igorr.lesson01.services.process.InterviewProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FaqXmlContext {

    private static final Logger LOG = LoggerFactory.getLogger(FaqXmlContext.class);

    public static void main(String... args) {


        LOG.info("Start ...");

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/app-context.xml");

        InterviewProcess interviewProcess = ctx.getBean("interview", InterviewProcess.class);
        interviewProcess.process();

    }
}
