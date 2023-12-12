package com.example.MoneyMind.controllers;

import com.example.MoneyMind.dtos.EssentialExpensesDTO;
import com.example.MoneyMind.service.EssentialExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses/essential")
public class OutgoingEssentialController {

    @Autowired
    private EssentialExpensesService essentialExpensesService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody EssentialExpensesDTO essentialExpensesDTO) {
        essentialExpensesService.create(essentialExpensesDTO);
        return ResponseEntity.ok("Successfully inserted");
    }

    @PutMapping("/{idEssentialExpenses}")
    public ResponseEntity<String> update(@PathVariable Long idEssentialExpenses,
                                         @RequestBody EssentialExpensesDTO essentialExpensesDTO) {
        essentialExpensesService.update(idEssentialExpenses, essentialExpensesDTO);
        return ResponseEntity.ok("Updated successfully");
    }

    @PostMapping("/filter")
    public ResponseEntity<List<EssentialExpensesDTO>> filter(@RequestBody String description) {
        return ResponseEntity.ok(essentialExpensesService.filter(description));
    }

    @GetMapping
    public ResponseEntity<Page<EssentialExpensesDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(essentialExpensesService.findAll(pageable));
    }


}
