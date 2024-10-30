package com.example.moneymind.controllers;

import com.example.moneymind.dtos.ExpenseLimitDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.ExpenseLimit;
import com.example.moneymind.service.LimitsService;
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
@RequestMapping("/limit")
@RequiredArgsConstructor
public class ExpenseLimitController {

    private final LimitsService limitsService;

    @Operation(summary = "Add expense limit", description = "Endpoint to add expense limit.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Category does not belong to the informed user")
    })
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody ExpenseLimit expenseLimit,
                                         @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        limitsService.create(expenseLimit, jwtTokenDTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{idLimit}")
    public ResponseEntity<String> editLimit(@PathVariable Long idLimit,
                                            @RequestBody ExpenseLimitDTO expenseLimitDTO,
                                            @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        limitsService.editLimit(idLimit, expenseLimitDTO, jwtTokenDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{idLimit}")
    public ResponseEntity<Void> deleteById(@AuthenticationPrincipal JwtTokenDTO jwtTokenDTO,
                                           @PathVariable Long idLimit) {
        limitsService.deleteById(idLimit, jwtTokenDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/check-limit/{idLimit}/before-updating")
    public ResponseEntity<String> checkLimitBeforeUpdatingLimit(@PathVariable Long idLimit,
                                                                @RequestBody ExpenseLimitDTO expenseLimitDTO,
                                                                @AuthenticationPrincipal JwtTokenDTO jwtTokenDTO) {
        String message = limitsService.checkLimitBeforeUpdatingLimit(idLimit, expenseLimitDTO, jwtTokenDTO.getId());
        return ResponseEntity.ok(message);
    }
}
