package com.example.moneymind.controllers;

import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @Operation(summary = "Buscar saldo total")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o saldo total do usuário pelo id do token")
    })
    @GetMapping("/saldo-total")
    public ResponseEntity<BigDecimal> buscarSaldoTotal(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        return ResponseEntity.ok(relatorioService.buscarSaldoTotal(jwtTokenDTO.getId()));

    }
}
