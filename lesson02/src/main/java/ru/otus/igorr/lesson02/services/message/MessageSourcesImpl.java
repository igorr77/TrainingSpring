package ru.otus.igorr.lesson02.services.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service("messageSources")
public class MessageSourcesImpl implements MessageSources {

    private static final Logger LOG = LoggerFactory.getLogger(MessageSourcesImpl.class);

    private final MessageSource[] messageSources;
    private final Locale locale;

    @Autowired
    public MessageSourcesImpl(final MessageSource[] messageSources, Locale locale) {
        this.messageSources = messageSources;
        this.locale = locale;
    }

    @Override
    public String getMessage(final String s) {
        return getMessage(new DefaultMessageSourceResolvable(s));
    }


    @Override
    public String getMessage(final MessageSourceResolvable messageSourceResolvable) {
        return findMessageInBundles(messageSourceResolvable);
    }

    @Override
    public boolean hasMessage(final MessageSource messageSource, final MessageSourceResolvable messageSourceResolvable) {
        // TODO: 01.03.19 Проверку при нескольких контекстах
        return true;
    }

    private String findMessageInBundles(final MessageSourceResolvable messageSourceResolvable) {
        for (MessageSource messageSource : this.messageSources) {
            if (hasMessage(messageSource, messageSourceResolvable)) {
                return messageSource.getMessage(messageSourceResolvable, locale);
            }
        }
        return messageSourceResolvable.getCodes()[0].concat(" No found");
    }

}
