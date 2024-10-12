package com.example.moneymind.config.security;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Users;
import com.example.moneymind.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

import static com.example.moneymind.config.exception.ExceptionMessages.USER_NOT_FOUND;
import static com.example.moneymind.config.security.TokenService.extractEmail;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try{

            if (request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/user/register")){
                filterChain.doFilter(request, response);
                return;
            }
            var token = this.recoverToken(request);
            var login = tokenService.validateToken(token);

            if (login != null) {
                Users user = userRepository.findByEmail(extractEmail(token)).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

                var jwtTokenDTO = new JwtTokenDTO(user.getId(), user.getEmail(), user.getUsername());

                var authentication = new UsernamePasswordAuthenticationToken(jwtTokenDTO, null, authorities);
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