package ru.otus.igorr.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.igorr.lesson02.services.process.InterviewProcess;

import java.util.Locale;

@Configuration
@ComponentScan("ru.otus.igorr.lesson02")
@PropertySource("classpath:app.properties")
public class InterviewApplication {

    private static final Logger LOG = LoggerFactory.getLogger(InterviewApplication.class);

    @Value("${message.bundle}")
    String messageBundle;

    @Value("${locale.language}")
    String language;
    @Value("${locale.country}")
    String country;


    public static void main(String... args) {


        LOG.info("Start ...");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(InterviewApplication.class);

        InterviewProcess interviewProcess = ctx.getBean(InterviewProcess.class);
        interviewProcess.process();

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("locale")
    public Locale getLocale() {
        return new Locale(language, country);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(messageBundle);
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
