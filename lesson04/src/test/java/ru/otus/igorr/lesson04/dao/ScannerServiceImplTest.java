package ru.otus.igorr.lesson04.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScannerServiceImplTest {

    ScannerService scanner;

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    void nextIntTest(int param) {
        ByteArrayInputStream input = new ByteArrayInputStream(String.valueOf(param).getBytes());
        System.setIn(input);

        scanner = new ScannerServiceImpl();
        int in = scanner.nextInt();
        assertEquals( param, in);
    }

    @Test
    void nextInt1Test() {
        String param = "a\n1";
        ByteArrayInputStream input = new ByteArrayInputStream(param.getBytes());
        System.setIn(input);

        scanner = new ScannerServiceImpl();
        int in = scanner.nextInt();
        assertEquals(1, in);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11111","22222","333333"})
    void nextLineTest(String param) {
        ByteArrayInputStream input = new ByteArrayInputStream(param.getBytes());
        System.setIn(input);

        scanner = new ScannerServiceImpl();
        String in = scanner.nextLine();
        assertEquals( param, in);
    }
}