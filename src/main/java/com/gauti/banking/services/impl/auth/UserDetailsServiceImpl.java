package com.gauti.banking.services.impl.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gauti.banking.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository; 

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        repository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided email"));
        return null;
    }

}
