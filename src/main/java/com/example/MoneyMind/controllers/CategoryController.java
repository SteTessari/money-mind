package com.example.MoneyMind.controllers;

import com.example.MoneyMind.entidades.Category;
import com.example.MoneyMind.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Create category", description = "Endpoint to create a new category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully")
    })
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Category category){
        categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
    }
}
