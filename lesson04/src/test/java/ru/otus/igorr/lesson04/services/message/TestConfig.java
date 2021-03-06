package ru.otus.igorr.lesson04.services.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.igorr.lesson04.config.MessagesProps;

//@Configuration
class TestConfig {

    //@Bean
    static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    //@Bean
    static MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ///ms.setBasename("/message/app_message");
        ms.setBasename("${message.bundle}");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    //@Bean
    MessageSources messageService() {
        MessageSource[] messageSources = new MessageSource[1];
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/messages/app_message");
        ms.setDefaultEncoding("UTF-8");
        messageSources[0] = ms;

        MessagesProps props = new MessagesProps();
        props.setLanguage("en");
        props.setCountry("GB");

        return new MessageSourcesImpl(messageSources, props);
    }


}
