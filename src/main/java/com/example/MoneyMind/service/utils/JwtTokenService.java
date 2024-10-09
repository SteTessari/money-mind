package com.example.MoneyMind.service.utils;

import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.config.security.TokenService;
import com.example.MoneyMind.dtos.authentication.JwtTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    @Autowired
    private TokenService tokenService;

    public JwtTokenDTO getJwtTokenDTO(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                return tokenService.validateToken(token);
            } catch (MoneyMindException e) {
                throw new MoneyMindException(HttpStatus.UNAUTHORIZED, "Inválid token.");
            }
        } else {
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Authorization header not found.");
        }
    }
}
