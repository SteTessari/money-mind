package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.UsuarioDTO;
import com.example.MoneyMind.service.CadastroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ObservationAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/usuario")
    public ResponseEntity<String> login(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha());
        authenticationManager.authenticate(token);
        cadastroUsuarioService.cadastrar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");
    }


//    @PostMapping("/usuario")
//    public ResponseEntity<String> buscarTodos(@Valid @RequestBody UsuarioDTO usuarioDTO){
//        cadastroUsuarioService.cadastrar(usuarioDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");
//    }


}
