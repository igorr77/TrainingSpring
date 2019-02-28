package ru.otus.igorr.lesson02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.igorr.lesson02.dao.ScannerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class AppTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mainTest() throws FileNotFoundException {
        System.out.println("main");
        String[] args = null;

        final InputStream original = System.in;

        String pathToData = this.getClass().getClassLoader().
                getResource("data/testmain.in").toString();
        final FileInputStream fips = new FileInputStream(new File(pathToData.substring(5)));
        System.setIn(fips);
        ScannerService.getInstance().setInputStream(System.in);
        InterviewApplicationContext.main(args);
        System.setIn(original);
    }
}