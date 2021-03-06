package ru.otus.igorr.lesson05.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.otus.igorr.lesson05.domain.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("User Name");
    }

    @ParameterizedTest
    @ValueSource(strings = {"AAA","BBB"})
    void complexSetGetNameTest(String param) {
        user.setName(param);
        assertEquals(param, user.getName());

    }
}