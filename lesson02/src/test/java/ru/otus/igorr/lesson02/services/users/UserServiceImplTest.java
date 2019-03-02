package ru.otus.igorr.lesson02.services.users;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.igorr.lesson02.dao.ScannerService;
import ru.otus.igorr.lesson02.domain.user.User;
import ru.otus.igorr.lesson02.services.message.MessageSources;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    UserServiceImpl service;

    @Mock
    MessageSources messageService;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(messageService);
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ValueSource(strings = ("Ivan Ivanov"))
    void getUserTest(String param) {

        ByteArrayInputStream input = new ByteArrayInputStream(param.getBytes());
        System.setIn(input);

        ScannerService.getInstance().setInputStream(System.in);

        when(messageService.getMessage(any(String.class))).thenReturn("message");

        User user = service.getUser();
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals(param, user.getName())

        );
    }
}