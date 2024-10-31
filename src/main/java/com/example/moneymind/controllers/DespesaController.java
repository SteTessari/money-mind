package com.example.moneymind.controllers;

import com.example.moneymind.dtos.DespesaDTO;
import com.example.moneymind.dtos.VerificarDespesaDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @Operation(summary = "Adicionar despesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Despesa adicionada com sucesso")
    })
    @PostMapping
    public ResponseEntity<String> criar(@Valid @RequestBody DespesaDTO despesaDTO,
                                        @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        despesaService.criar(despesaDTO, jwtTokenDTO);
        return ResponseEntity.ok("Adicionada com sucesso!");
    }

    @Operation(summary = "Atualizar despesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Despesa não encontrada"),
            @ApiResponse(responseCode = "200", description = "Despesa atualizada com sucesso")
    })
    @PutMapping("/{idDespesa}")
    public ResponseEntity<String> editar(@PathVariable Long idDespesa,
                                         @RequestBody DespesaDTO despesaDTO) {
        despesaService.editar(idDespesa, despesaDTO);
        return ResponseEntity.ok("Atualizada com sucesso!");
    }


    @Operation(summary = "Filtrar despesa por descrição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna as despesas filtradas")
    })
    @PostMapping("/filtrar")
    public ResponseEntity<List<DespesaDTO>> filtrar(@RequestParam("descricao") String descricao) {
        return ResponseEntity.ok(despesaService.filtrar(descricao));
    }

    @Operation(summary = "Buscar todas as despesas do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todas as despesas do usuário")
    })
    @GetMapping
    public ResponseEntity<Page<DespesaDTO>> buscarTodas(@PageableDefault Pageable pageable,
                                                        @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        Long idUsuario = jwtTokenDTO.getId();
        return ResponseEntity.ok(despesaService.buscarTodas(idUsuario, pageable));
    }

    @GetMapping("/verificar-limite/antes-de-inserir")
    public ResponseEntity<String> verificarLimiteAntesDeInserir(@Valid @RequestBody VerificarDespesaDTO verificarDespesaDTO,
                                                                @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        String mensagem = despesaService.verificarLimiteAntesDeInserir(verificarDespesaDTO, jwtTokenDTO.getId());
        return ResponseEntity.ok(mensagem);
    }

}
