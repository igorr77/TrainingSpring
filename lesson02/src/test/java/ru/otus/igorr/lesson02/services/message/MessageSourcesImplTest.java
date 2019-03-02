package ru.otus.igorr.lesson02.services.message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class MessageSourcesImplTest {


    @Autowired
    MessageSources messageService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMessage() {
        assertNotNull(messageService.getMessage("input.name"));
    }

    @Test
    void getMessage1() {
    }

    @Test
    void getMessage2() {
    }

    @Test
    void hasMessage() {
    }
}