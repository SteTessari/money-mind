package com.example.MoneyMind.controllers;

import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.config.security.TokenService;
import com.example.MoneyMind.dtos.authentication.JwtTokenDTO;
import com.example.MoneyMind.service.ReportService;
import com.example.MoneyMind.service.utils.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController extends JwtTokenService {

    @Autowired
    private ReportService reportService;

    @GetMapping("/total-balance")
    public ResponseEntity<?> findTotalBalance(@RequestHeader("Authorization") String authorizationHeader) {
        JwtTokenDTO jwtTokenDTO = getJwtTokenDTO(authorizationHeader);
        return ResponseEntity.ok(reportService.findTotalBalance(jwtTokenDTO.getId()));

    }
}
