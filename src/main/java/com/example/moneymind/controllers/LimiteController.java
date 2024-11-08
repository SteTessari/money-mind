package com.example.moneymind.controllers;

import com.example.moneymind.dtos.LimiteDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.service.LimiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/limite")
@RequiredArgsConstructor
public class LimiteController {

    private final LimiteService limiteService;

    @Operation(summary = "Adicionar limite em uma categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Categoria não encontrada para o usuário informado.")
    })
    @PostMapping
    public ResponseEntity<String> criar(@Valid @RequestBody LimiteDTO limiteDTO,
                                        @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        limiteService.criar(limiteDTO, jwtTokenDTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{idLimite}")
    public ResponseEntity<String> editar(@PathVariable Long idLimite,
                                         @RequestBody LimiteDTO limiteDTO,
                                         @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        limiteService.editar(idLimite, limiteDTO, jwtTokenDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{idLimite}")
    public ResponseEntity<Void> deletar(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO,
                                        @PathVariable Long idLimite) {
        limiteService.deletar(idLimite, jwtTokenDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/verificar-limite/{idLimite}/antes-de-atualizar")
    public ResponseEntity<String> verificarLimiteAntesDeAtualizar(@PathVariable Long idLimite,
                                                                  @RequestBody LimiteDTO limiteDTO,
                                                                  @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        String mensagem = limiteService.verificarLimiteAntesDeAtualizar(idLimite, limiteDTO, jwtTokenDTO.getId());
        return ResponseEntity.accepted().body(mensagem);
    }
}
