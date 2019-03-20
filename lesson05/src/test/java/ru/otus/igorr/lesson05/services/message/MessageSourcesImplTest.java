package ru.otus.igorr.lesson05.services.message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.igorr.lesson05.config.MessagesProps;
import ru.otus.igorr.lesson05.services.message.MessageSources;
import ru.otus.igorr.lesson05.services.message.MessageSourcesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MessageSourcesImplTest {


    @Autowired
    MessageSource messageSource;
    MessageSources messageService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMessageTest() {
        MessageSource[] messageSources = {messageSource};
        MessagesProps props = new MessagesProps();
        props.setLanguage("en");
        props.setCountry("GB");
        messageService = new MessageSourcesImpl(messageSources, props);

        assertNotNull(messageService.getMessage("input.name"));
    }


    @ParameterizedTest
    @ValueSource(strings = "input.name")
    void getMessage1Test(String param) {
        MessageSource[] messageSources = {};
        MessagesProps props = new MessagesProps();
        props.setLanguage("en");
        props.setCountry("GB");
        messageService = new MessageSourcesImpl(messageSources, props);

        assertEquals(param.concat(": Not found"), messageService.getMessage(param));
    }


    @Test
    void getMessage2() {
    }

    @Test
    void hasMessage() {
    }
}