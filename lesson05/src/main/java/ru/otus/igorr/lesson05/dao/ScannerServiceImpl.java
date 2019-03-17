package ru.otus.igorr.lesson05.dao;

import org.springframework.stereotype.Service;

import java.util.Scanner;


@Service
public class ScannerServiceImpl implements ScannerService {


    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public int nextInt() {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}
