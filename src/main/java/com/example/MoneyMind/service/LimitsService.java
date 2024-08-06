package com.example.MoneyMind.service;

import com.example.MoneyMind.entidades.ExpenseLimit;
import com.example.MoneyMind.repository.LimitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimitsService {
    @Autowired
    private LimitsRepository limitsRepository;
    @Autowired
    private UserService userService;

    public void create(ExpenseLimit expenseLimit) {
        userService.findById(expenseLimit.getIdUser());
    }

    public ExpenseLimit buscarLimiteExistente(Long idUser, Long idCategory, String month) {
        Optional<ExpenseLimit> limit = limitsRepository.findByIdUserAndIdCategoryAndMonth(idUser, idCategory, month);

        if (limit.isPresent())
            return limit.get();

        return null;
    }

}
