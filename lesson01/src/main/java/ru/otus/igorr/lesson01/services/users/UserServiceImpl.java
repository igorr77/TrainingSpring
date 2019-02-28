package ru.otus.igorr.lesson01.services.users;

import ru.otus.igorr.lesson01.domain.user.User;
import ru.otus.igorr.lesson01.dao.ScannerService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        ScannerService scanner = ScannerService.getInstance();
        System.out.println("Input name:");
        String name = scanner.nextLine();
        return new User(name);
    }
}
