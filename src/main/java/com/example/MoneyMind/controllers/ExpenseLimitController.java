package com.example.MoneyMind.controllers;

import com.example.MoneyMind.entidades.ExpenseLimit;
import com.example.MoneyMind.repository.LimitsRepository;
import com.example.MoneyMind.service.LimitsService;
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

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ExpenseLimit expenseLimit){
        limitsService.create(expenseLimit);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inserido com sucesso.");
    }

}
