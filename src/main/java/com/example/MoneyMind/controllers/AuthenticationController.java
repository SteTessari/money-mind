package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.authentication.AuthenticatedUserDTO;
import com.example.MoneyMind.dtos.authentication.LoginRequestDTO;
import com.example.MoneyMind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserDTO> login(@RequestBody LoginRequestDTO body) {
        return ResponseEntity.accepted().body(userService.login(body));
    }}
