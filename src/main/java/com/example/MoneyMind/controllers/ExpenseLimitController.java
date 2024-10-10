package com.example.MoneyMind.controllers;

import com.example.MoneyMind.entidades.ExpenseLimit;
import com.example.MoneyMind.service.LimitsService;
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
@RequestMapping("/limit")
public class ExpenseLimitController {

    @Autowired
    private LimitsService limitsService;

    @Operation(summary = "Add expense limit", description = "Endpoint to add expense limit.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Category does not belong to the informed user")
    })
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody ExpenseLimit expenseLimit) {
        limitsService.create(expenseLimit);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created.");
    }

}
