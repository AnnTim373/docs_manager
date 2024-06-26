package org.timofeeva.docs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.User;
import org.timofeeva.docs.error.NotFoundException;
import org.timofeeva.docs.repository.UserRepository;
import org.timofeeva.docs.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(() -> new NotFoundException("login"));
    }
}
