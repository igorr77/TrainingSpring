package ru.otus.igorr.lesson04.services.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import ru.otus.igorr.lesson04.config.MessagesProps;

import java.util.Locale;

@Service("messageSources")
@EnableConfigurationProperties(MessagesProps.class)
public class MessageSourcesImpl implements MessageSources {

    private static final Logger LOG = LoggerFactory.getLogger(MessageSourcesImpl.class);

    // хочу поработать с разными бандлами из одного сервиса
    private final MessageSource[] messageSources;
    private final MessagesProps props;

    @Autowired
    public MessageSourcesImpl(final MessageSource[] messageSources,
                              final MessagesProps props
    ) {
        this.messageSources = messageSources;
        this.props = props;
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
                return messageSource.getMessage(messageSourceResolvable, new Locale(props.getLanguage(), props.getCountry()));
            }
        }
        return messageSourceResolvable.getCodes()[0].concat(" No found");
    }

}
