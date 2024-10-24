package com.example.moneymind.controllers;

import com.example.moneymind.dtos.UpdateUserDTO;
import com.example.moneymind.dtos.UserDTO;
import com.example.moneymind.dtos.UserDataDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.service.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

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

    @Operation(summary = "Update user", description = "Endpoint to update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "400", description = "Please enter your password to update your email."),
            @ApiResponse(responseCode = "400", description = "Incorrect password.")
    })
    @PutMapping("/update")
    public ResponseEntity<Void> update(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO,
                                       @Valid @RequestBody UpdateUserDTO updateUserDTO){

        service.update(updateUserDTO, jwtTokenDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserDataDTO> findUser(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO){
        return ResponseEntity.ok(service.findUser(jwtTokenDTO.getId()));
    }

}
