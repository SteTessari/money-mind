package com.example.moneymind.service;

import com.example.moneymind.entidades.ExpenseLimit;
import com.example.moneymind.repository.LimitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitsService {
    private final LimitsRepository limitsRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public void create(ExpenseLimit expenseLimit) {
        userService.findById(expenseLimit.getIdUser());

        categoryService.validateUserCategory(expenseLimit.getIdCategory(), expenseLimit.getIdUser());

        limitsRepository.save(expenseLimit);
    }

    public ExpenseLimit buscarLimiteExistente(Long idUser, Long idCategory, String month) {
        Optional<ExpenseLimit> limit = limitsRepository.findByIdUserAndIdCategoryAndMonth(idUser, idCategory, month);

        if (limit.isPresent())
            return limit.get();

        return null;
    }

}
