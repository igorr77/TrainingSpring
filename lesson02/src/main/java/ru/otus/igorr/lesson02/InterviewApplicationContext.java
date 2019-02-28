package ru.otus.igorr.lesson02;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.otus.igorr.lesson02.services.process.InterviewProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan("ru.otus.igorr.lesson02")
@PropertySource("classpath:app.properties")
public class InterviewApplicationContext {

    private static final Logger LOG = LoggerFactory.getLogger(InterviewApplicationContext.class);

    public static void main(String... args) {


        LOG.info("Start ...");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(InterviewApplicationContext.class);

        InterviewProcess interviewProcess = ctx.getBean(InterviewProcess.class);
        interviewProcess.process();

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/message/app_message");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
