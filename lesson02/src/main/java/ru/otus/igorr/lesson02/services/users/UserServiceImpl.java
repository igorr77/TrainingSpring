package ru.otus.igorr.lesson02.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson02.dao.ScannerService;
import ru.otus.igorr.lesson02.domain.user.User;
import ru.otus.igorr.lesson02.services.message.MessageSources;

@Service
public class UserServiceImpl implements UserService {

    private MessageSources messageService;

    @Autowired
    public UserServiceImpl(MessageSources messageServices) {
        this.messageService = messageServices;
    }

    @Override
    public User getUser() {
        ScannerService scanner = ScannerService.getInstance();
        System.out.println(messageService.getMessage("input.name"));
        String name = scanner.nextLine();
        return new User(name);
    }
}
