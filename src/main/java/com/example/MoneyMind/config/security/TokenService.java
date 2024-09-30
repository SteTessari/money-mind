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
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Para conversão JSON

    public String generateToken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Define o cabeçalho personalizado
            Map<String, Object> headerClaims = new HashMap<>();
            headerClaims.put("typ", "JWT"); // Tipo do token
            headerClaims.put("alg", "HS256"); // Algoritmo

            return JWT.create()
                    .withHeader(headerClaims) // Adiciona o cabeçalho personalizado
                    .withIssuer("login-auth-api")
                    .withClaim("idUser", user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
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

            // Extrai as claims personalizadas
            Long idUser = decodedJWT.getClaim("idUser").asLong();
            String email = decodedJWT.getClaim("email").asString();
            String username = decodedJWT.getClaim("username").asString();

            // Cria e retorna o DTO
            return new JwtTokenDTO(idUser, username, email);

        } catch (JWTVerificationException e) {
            return null; // Ou lançar uma exceção personalizada
        }
    }


    public static String extractUsername(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("username").asString();
        } catch (Exception e) {
            return null; // Ou lançar uma exceção personalizada
        }
    }
    public static Long extractUserId(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String idUser = decodedJWT.getClaim("idUser").asString();
            return Long.valueOf(idUser);
        } catch (Exception e) {
            return null; // Ou lançar uma exceção personalizada
        }
    }
    public static String extractEmail(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("email").asString();
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
