package com.example.MoneyMind.controllers;

import com.example.MoneyMind.entidades.Category;
import com.example.MoneyMind.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Category category){
        categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
    }
}
