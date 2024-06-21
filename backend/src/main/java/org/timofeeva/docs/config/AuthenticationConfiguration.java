package org.timofeeva.docs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.timofeeva.docs.security.UsernamePasswordDetailService;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfiguration {

    private final UsernamePasswordDetailService usernamePasswordDetailService;

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider userAuthenticationProvider = new DaoAuthenticationProvider();
        userAuthenticationProvider.setUserDetailsService(usernamePasswordDetailService);
        userAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return userAuthenticationProvider;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
