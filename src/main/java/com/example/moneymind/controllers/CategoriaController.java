package com.example.moneymind.controllers;

import com.example.moneymind.dtos.CategoriaDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Categoria;
import com.example.moneymind.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Criar nova categoria",
            description = "O endpoint cria uma nova categoria caso não exista uma com a mesma descrição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    })
    @PostMapping
    public ResponseEntity<String> criar(@Valid @RequestBody CategoriaDTO categoriaDTO,
                                        @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        categoriaService.criar(categoriaDTO, jwtTokenDTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Criado com sucesso");
    }

    @Operation(summary = "Buscar todas as categorias do usuário",
            description = "Busca todas as categorias pelo id do usuário no token")
    @GetMapping("/todas")
    public ResponseEntity<List<Categoria>> buscarTodas(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        return ResponseEntity.ok(categoriaService.buscarTodas(jwtTokenDTO.getId()));
    }

    @Operation(summary = "Atualizar categoria por id",
            description = "Atualiza a categoria do usuário logado com base no idUsuario do token. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "204", description = "Categoria não encontrada")
    })
    @PutMapping("/{idCategoria}")
    public ResponseEntity<Void> editar(@PathVariable Long idCategoria,
                                       @RequestBody CategoriaDTO categoriaDTO,
                                       @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        categoriaService.editar(idCategoria, categoriaDTO, jwtTokenDTO.getId());
        return ResponseEntity.ok().build();
    }
}
