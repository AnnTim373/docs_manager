package org.timofeeva.docs.service;

import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.User;

@Service
public interface UserService {

    User findByLogin(String login);

}
