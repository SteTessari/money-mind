package com.example.moneymind.controllers;

import com.example.moneymind.entidades.Category;
import com.example.moneymind.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> create(@Valid @RequestBody Category category){
        categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
    }
}
