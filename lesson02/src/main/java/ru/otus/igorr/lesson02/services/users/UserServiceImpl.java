package ru.otus.igorr.lesson02.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson02.domain.user.User;
import ru.otus.igorr.lesson02.dao.ScannerService;

import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    private MessageSource messageSource;

    @Autowired
    public UserServiceImpl(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @Override
    public User getUser() {
        ScannerService scanner = ScannerService.getInstance();
        System.out.println(messageSource.getMessage("input.name", new String[] {":"}, Locale.ENGLISH) );
        String name = scanner.nextLine();
        return new User(name);
    }
}
