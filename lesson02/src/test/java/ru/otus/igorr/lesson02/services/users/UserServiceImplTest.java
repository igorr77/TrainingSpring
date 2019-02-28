package ru.otus.igorr.lesson02.services.users;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.igorr.lesson02.domain.user.User;
import ru.otus.igorr.lesson02.dao.ScannerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    UserServiceImpl service;

    @Mock
    MessageSource messageSource;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(messageSource);
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

        when(messageSource.getMessage(any(String.class), eq(new String[] {":"}), any(Locale.class))).thenReturn("message");

        User user = service.getUser();
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals(param, user.getName())

        );
    }
}