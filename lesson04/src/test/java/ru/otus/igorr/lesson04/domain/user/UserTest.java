package ru.otus.igorr.lesson04.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("User Name");
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"AAA","BBB"})
    void complexSetGetNameTest(String param) {
        user.setName(param);
        assertEquals(param, user.getName());

    }
}