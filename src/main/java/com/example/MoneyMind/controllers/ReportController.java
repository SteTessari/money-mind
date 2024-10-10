package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.authentication.JwtTokenDTO;
import com.example.MoneyMind.service.ReportService;
import com.example.MoneyMind.service.utils.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Find total balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the user's total balance")
    })
    @GetMapping("/total-balance")
    public ResponseEntity<?> findTotalBalance(@RequestHeader("Authorization") String authorizationHeader) {
        JwtTokenDTO jwtTokenDTO = getJwtTokenDTO(authorizationHeader);
        return ResponseEntity.ok(reportService.findTotalBalance(jwtTokenDTO.getId()));

    }
}
