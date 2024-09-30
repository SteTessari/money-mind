package com.example.MoneyMind.controllers;

import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.config.security.TokenService;
import com.example.MoneyMind.dtos.authentication.JwtTokenDTO;
import com.example.MoneyMind.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.MoneyMind.config.security.TokenService.extractUserId;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/total-balance")
    public ResponseEntity<?> findTotalBalance(@RequestHeader("Authorization") String authorizationHeader) {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                JwtTokenDTO userDetails = tokenService.validateToken(token);

                return ResponseEntity.ok(reportService.findTotalBalance(userDetails.getId()));
            } catch (MoneyMindException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cabeçalho de autorização não encontrado.");
        }
    }
}
