package com.example.medkit.services.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class MessageSourceService {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key, String language) {
        String result;
        try {
            Locale locale = Locale.forLanguageTag(language);
            result = messageSource.getMessage(key, null, locale);
        } catch (MissingResourceException e) {
            result = "Belgilanmagan";
        }
        return result;
    }
}
