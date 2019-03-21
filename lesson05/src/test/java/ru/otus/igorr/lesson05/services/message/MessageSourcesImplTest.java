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
    @Autowired
    MessageSources messageService;
    @Autowired
    MessagesProps messagesProps;


    @ParameterizedTest
    @ValueSource(strings = "input.name")
    void getMessageTest(String param) {
        assertNotNull(messageService.getMessage(param));
    }

}