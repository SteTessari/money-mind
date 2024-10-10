package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.ExpenseDTO;
import com.example.MoneyMind.dtos.authentication.JwtTokenDTO;
import com.example.MoneyMind.service.ExpensesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial/expense")
public class FinancialExpenseController {

    @Autowired
    private ExpensesService expensesService;

    @Operation(summary = "Add expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expense added successfully"),
            @ApiResponse(responseCode = "400", description = "The expense amount is greater than the defined limit")
    })
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody ExpenseDTO expenseDTO) {
        expensesService.create(expenseDTO);
        return ResponseEntity.ok("Successfully inserted");
    }

    @Operation(summary = "Update expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Expense not found"),
            @ApiResponse(responseCode = "200", description = "Updated successfully")
    })
    @PutMapping("/{idExpense}")
    public ResponseEntity<String> update(@PathVariable Long idExpense,
                                         @RequestBody ExpenseDTO expenseDTO) {
        expensesService.update(idExpense, expenseDTO);
        return ResponseEntity.ok("Updated successfully");
    }


    @Operation(summary = "Filter expenses by description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns filtered expenses")
    })
    @PostMapping("/filter")
    public ResponseEntity<List<ExpenseDTO>> filter(@RequestBody String description) {
        return ResponseEntity.ok(expensesService.filter(description));
    }

    @Operation(summary = "Search all user expenses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all expenses")
    })
    @GetMapping
    public ResponseEntity<Page<ExpenseDTO>> findAll(@PageableDefault Pageable pageable, @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        Long idUser = jwtTokenDTO.getId();
        return ResponseEntity.ok(expensesService.findAll(idUser, pageable));
    }


}
