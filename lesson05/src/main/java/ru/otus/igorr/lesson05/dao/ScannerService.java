package ru.otus.igorr.lesson05.dao;

/*
import java.io.InputStream;
import java.util.Scanner;

public class ScannerService {

    private Scanner scanner;

    private static ScannerService instance = new ScannerService();

    public static ScannerService getInstance() {
        return instance;
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
*/
public interface ScannerService {
    String nextLine();

    int nextInt();
}