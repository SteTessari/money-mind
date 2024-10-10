package com.example.MoneyMind.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.dtos.authentication.JwtTokenDTO;
import com.example.MoneyMind.entidades.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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


    public String generateToken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            Map<String, Object> headerClaims = new HashMap<>();
            headerClaims.put("typ", "JWT");
            headerClaims.put("alg", "HS256");

            return JWT.create()
                    .withHeader(headerClaims)
                    .withIssuer("login-auth-api")
                    .withClaim("idUser", user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new MoneyMindException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating token.");
        }
    }


    public JwtTokenDTO validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token);

            Long idUser = decodedJWT.getClaim("idUser").asLong();
            String email = decodedJWT.getClaim("email").asString();
            String username = decodedJWT.getClaim("username").asString();

            return new JwtTokenDTO(idUser, username, email);

        } catch (JWTVerificationException e) {
            return null;
        }
    }
    public static String extractEmail(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("email").asString();
        } catch (Exception e) {
            return null;
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
            return true;
        }
    }
}
