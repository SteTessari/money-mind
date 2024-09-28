package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.ExpenseDTO;
import com.example.MoneyMind.service.EssentialExpensesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses/essential")
public class FinancialExpenseController {

    @Autowired
    private EssentialExpensesService essentialExpensesService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody ExpenseDTO expenseDTO) {
        essentialExpensesService.create(expenseDTO);
        return ResponseEntity.ok("Successfully inserted");
    }

    @PutMapping("/{idEssentialExpenses}")
    public ResponseEntity<String> update(@PathVariable Long idEssentialExpenses,
                                         @RequestBody ExpenseDTO expenseDTO) {
        essentialExpensesService.update(idEssentialExpenses, expenseDTO);
        return ResponseEntity.ok("Updated successfully");
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ExpenseDTO>> filter(@RequestBody String description) {
        return ResponseEntity.ok(essentialExpensesService.filter(description));
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(essentialExpensesService.findAll(pageable));
    }


}
