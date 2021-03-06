package com.netcraker.services.impl;

import com.netcraker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<com.netcraker.model.User> userOptFromDb = userRepository.findByEmail(email);

        final com.netcraker.model.User userFromDb = userOptFromDb
                .orElseThrow(() -> new UsernameNotFoundException(email));
        if (userFromDb.getEnabled()) {
            return new User(userFromDb.getEmail(), userFromDb.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException(email);
    }
}
