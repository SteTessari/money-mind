package com.example.moneymind.service;

import com.example.moneymind.entidades.ExpenseLimit;
import com.example.moneymind.repository.LimitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimitsService {
    @Autowired
    private LimitsRepository limitsRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

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
