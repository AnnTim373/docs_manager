package org.timofeeva.docs.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.timofeeva.docs.domain.User;
import org.timofeeva.docs.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Component("usernamePasswordDetailService")
@RequiredArgsConstructor
public class UsernamePasswordDetailService implements UserDetailsService {


    @ReadOnlyProperty
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.info("Authenticating {}", login);

        Optional<User> userFromDB = userRepository.findByLogin(login);
        return userFromDB.map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }

}
