package ru.otus.igorr.lesson05.services.users;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.igorr.lesson05.dao.ScannerService;
import ru.otus.igorr.lesson05.domain.user.User;
import ru.otus.igorr.lesson05.services.message.MessageSources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService service;

    @MockBean
    MessageSources messageService;
    @MockBean
    ScannerService scannerService;


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