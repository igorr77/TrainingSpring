package ru.otus.igorr.lesson05;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.igorr.lesson05.config.MessagesProps;
import ru.otus.igorr.lesson05.services.process.InterviewProcess;

import java.util.Arrays;

@Configuration
//@SpringBootApplication
@EnableConfigurationProperties(MessagesProps.class)
public class Lesson05Application {

    private static final Logger LOG = LoggerFactory.getLogger(Lesson05Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Lesson05Application.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        InterviewProcess interviewProcess = ctx.getBean(InterviewProcess.class);
        interviewProcess.process();
    }


    @Bean
    public MessageSource messageSource(MessagesProps props) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(props.getBundle());
        ms.setDefaultEncoding("UTF-8");
        LOG.debug("===> {}", props.getBundle());
        return ms;
    }

}
