package com.example.MoneyMind.config.security;

import com.example.MoneyMind.config.exception.ExceptionMessages;
import com.example.MoneyMind.entidades.Users;
import com.example.MoneyMind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = this.usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());

    }
}