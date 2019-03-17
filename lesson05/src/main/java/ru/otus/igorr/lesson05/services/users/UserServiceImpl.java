package ru.otus.igorr.lesson05.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson05.dao.ScannerService;
import ru.otus.igorr.lesson05.domain.user.User;
import ru.otus.igorr.lesson05.services.message.MessageSources;

@Service
public class UserServiceImpl implements UserService {

    private final MessageSources messageService;
    private final ScannerService scanner;

    @Autowired
    public UserServiceImpl(MessageSources messageServices, ScannerService scannerService) {
        this.messageService = messageServices;
        this.scanner = scannerService;
    }

    @Override
    public User getUser() {
        //ScannerService scanner = ScannerService.getInstance();
        System.out.println(messageService.getMessage("input.name"));
        String name = scanner.nextLine();
        return new User(name);
    }
}
