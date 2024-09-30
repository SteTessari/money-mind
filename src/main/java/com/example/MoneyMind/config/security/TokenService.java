package com.example.MoneyMind.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MoneyMind.dtos.authentication.JwtTokenDTO;
import com.example.MoneyMind.entidades.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Para conversão JSON

    public String generateToken(Users user) {
        JwtTokenDTO userDto = new JwtTokenDTO(user.getId(), user.getUsername(), user.getEmail()); // Cria o DTO
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String subject = objectMapper.writeValueAsString(userDto); // Serializa o DTO em JSON

            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(subject)
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException | JsonProcessingException e) {
            throw new RuntimeException("Erro ao gerar o token.", e);
        }
    }

    public JwtTokenDTO validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token);

            // Deserializa o subject (JSON) para o DTO
            String subject = decodedJWT.getSubject();
            return objectMapper.readValue(subject, JwtTokenDTO.class);
        } catch (JWTVerificationException | JsonProcessingException e) {
            return null;
        }
    }

    public String extractUsername(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            JwtTokenDTO userDto = objectMapper.readValue(decodedJWT.getSubject(), JwtTokenDTO.class);
            return userDto.getUsername(); // Retorna o username do DTO
        } catch (Exception e) {
            return null; // Ou lançar uma exceção personalizada
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt().before(new java.util.Date());
        } catch (Exception e) {
            return true; // Se ocorrer um erro, considera o token expirado
        }
    }
}
