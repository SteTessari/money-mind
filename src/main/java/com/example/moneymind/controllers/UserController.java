package com.example.moneymind.controllers;

import com.example.moneymind.dtos.AtualizarUsuarioDTO;
import com.example.moneymind.dtos.UserDataDTO;
import com.example.moneymind.dtos.UsuarioDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.service.UsuarioService;
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
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService service;

    @Operation(
            summary = "Cadastrar usuário",
            description = "Cadastra um novo usuário."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "O username já existe"),
            @ApiResponse(responseCode = "400", description = "Senha inválida"),
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.")
    })
    @PostMapping
    public ResponseEntity<Long> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(usuarioDTO));
    }

    @Operation(summary = "Editar dados do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "400", description = "Por favor insira sua senha atual para atualizar o email."),
            @ApiResponse(responseCode = "400", description = "Senha incorreta.")
    })
    @PutMapping("/editar")
    public ResponseEntity<Void> editar(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO,
                                       @Valid @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO) {

        service.editar(atualizarUsuarioDTO, jwtTokenDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Buscar dados do usuário", description = "Busca os dados do usuário logado pelo id do usuário no token.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @GetMapping("/buscar")
    public ResponseEntity<UserDataDTO> buscarUsuario(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        return ResponseEntity.ok(service.buscarDadosUsuarioPorId(jwtTokenDTO.getId()));
    }

}
