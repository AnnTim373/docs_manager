package org.timofeeva.docs.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public interface LocalizedMessageService {

    String getLocalizedMessageWithArgs(String key, Object[] args, NativeWebRequest request);

    String getLocalizedMessage(String key, NativeWebRequest request);

}
