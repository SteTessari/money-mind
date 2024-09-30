package com.example.MoneyMind.config.security;

import com.example.MoneyMind.config.exception.ExceptionMessages;
import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.entidades.Users;
import com.example.MoneyMind.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static com.example.MoneyMind.config.security.TokenService.extractEmail;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            var token = this.recoverToken(request);
            var login = tokenService.validateToken(token);

            if (login != null) {
                Users user = userRepository.findByEmail(extractEmail(token)).orElseThrow(() -> new RuntimeException(ExceptionMessages.USER_NOT_FOUND));
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (MoneyMindException e) {
            throw e;
        } catch (Exception e) {
            throw new MoneyMindException(HttpStatus.UNAUTHORIZED, "Unauthorized: Invalid token");
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}