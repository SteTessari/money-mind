package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.UserDTO;
import com.example.MoneyMind.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(
            summary = "Register User",
            description = "Endpoint to register a new user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "The username already exists"),
            @ApiResponse(responseCode = "400", description = "Invalid password"),
            @ApiResponse(responseCode = "201", description = "User created successfully")
    })
    @PostMapping("/register")
    public ResponseEntity<Long> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userDTO));
    }

}
