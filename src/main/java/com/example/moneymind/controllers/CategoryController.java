package com.example.moneymind.controllers;

import com.example.moneymind.dtos.CategoryDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Category;
import com.example.moneymind.service.CategoryService;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Create category",
            description = "Endpoint to create a new category. Create category only if it does not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully")
    })
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CategoryDTO categoryDTO,
                                         @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        categoryService.create(categoryDTO, jwtTokenDTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
    }

    @Operation(summary = "Search all user categories",
            description = "Search all categories of logged in user by token id")
    @GetMapping("/find-all")
    public ResponseEntity<List<Category>> findAll(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        return ResponseEntity.ok(categoryService.findAll(jwtTokenDTO.getId()));
    }
}
