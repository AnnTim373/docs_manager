package org.timofeeva.docs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.timofeeva.docs.service.LocalizedMessageService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
class LocalizedMessageServiceImpl implements LocalizedMessageService {

    private final MessageSource messageSource;

    @Override
    public String getLocalizedMessageWithArgs(String key, Object[] args, NativeWebRequest request) {
        String lang = request.getHeader("Language");
        if (lang == null) return messageSource.getMessage(key, args, Locale.ENGLISH);
        return messageSource.getMessage(key, args, Locale.forLanguageTag(lang));
    }

    @Override
    public String getLocalizedMessage(String key, NativeWebRequest request) {
        String lang = request.getHeader("Language");
        if (lang == null) return messageSource.getMessage(key, new Object[]{}, Locale.ENGLISH);
        return messageSource.getMessage(key, new Object[]{}, Locale.forLanguageTag(lang));
    }

}
