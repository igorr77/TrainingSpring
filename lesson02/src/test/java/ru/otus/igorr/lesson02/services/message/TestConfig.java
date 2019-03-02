package ru.otus.igorr.lesson02.services.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class TestConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    static MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ///ms.setBasename("/message/app_message");
        ms.setBasename("${message.bundle}");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    MessageSources messageService() {
        MessageSource[] messageSources = new MessageSource[1];
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/message/app_message");
        ms.setDefaultEncoding("UTF-8");
        messageSources[0] = ms;

        return new MessageSourcesImpl(messageSources);
    }


}
