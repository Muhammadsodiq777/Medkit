package com.example.medkit.services.source;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.MissingResourceException;

@Service
public class MessageSourceService {
    private final MessageSource messageSource;

    public MessageSourceService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, String language) {
        String result;
        try {
            result = messageSource.getMessage(key, null, Locale.forLanguageTag(language));
        } catch (MissingResourceException e) {
            result = "Не определен";
        }
        return result;
    }
}
