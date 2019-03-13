package ru.otus.igorr.lesson04.services.users;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.igorr.lesson04.dao.ScannerService;
import ru.otus.igorr.lesson04.domain.user.User;
import ru.otus.igorr.lesson04.services.message.MessageSources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl service;

    @MockBean
    MessageSources messageService;
    @MockBean
    ScannerService scannerService;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(messageService, scannerService);
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ValueSource(strings = ("Ivan Ivanov"))
    void getUserTest(String param) {

        when(messageService.getMessage(any(String.class))).thenReturn("message");
        when(scannerService.nextLine()).thenReturn(param);

        User user = service.getUser();
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals(param, user.getName())

        );
    }
}