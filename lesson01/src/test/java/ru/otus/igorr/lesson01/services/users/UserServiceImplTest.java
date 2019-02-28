package ru.otus.igorr.lesson01.services.users;

import ru.otus.igorr.lesson01.domain.user.User;
import ru.otus.igorr.lesson01.dao.ScannerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    UserServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl();
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

        User user = service.getUser();
        assertNotNull(user);
    }
}