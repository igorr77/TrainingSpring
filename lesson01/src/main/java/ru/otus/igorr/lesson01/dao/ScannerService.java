package ru.otus.igorr.lesson01.dao;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerService {

    private static Scanner scanner;

    private static ScannerService Instance = new ScannerService();

    public static ScannerService getInstance() {
        return Instance;
    }

    private ScannerService() {
        scanner = new Scanner(System.in);
    }

    public String nextLine(){
        return scanner.nextLine();
    }

    public int nextInt(){
        return scanner.nextInt();
    }

    public void setInputStream(InputStream source ){
        scanner = new Scanner(source);
    }
}
