package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.ExpenseLimitDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.ExpenseLimit;
import com.example.moneymind.mapper.LimitMapper;
import com.example.moneymind.repository.LimitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitsService {
    private final LimitsRepository limitsRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final LimitMapper limitMapper = LimitMapper.INSTANCE;

    public void create(ExpenseLimit expenseLimit) {
        userService.findById(expenseLimit.getIdUser());

        categoryService.validateUserCategory(expenseLimit.getIdCategory(), expenseLimit.getIdUser());

        limitsRepository.save(expenseLimit);
    }

    public ExpenseLimit findById(Long idUser, Long idLimit) {
        Optional<ExpenseLimit> limitOptional = limitsRepository.findByIdAndIdUser(idLimit, idUser);

        if (limitOptional.isPresent()){
            return limitOptional.get();
        }else {
            throw new MoneyMindException(HttpStatus.NOT_FOUND, "Limit not found.");
        }
    }

    public void editLimit(Long idLimit,ExpenseLimitDTO expenseLimitDTO, JwtTokenDTO jwtTokenDTO) {
        ExpenseLimit expenseLimit = findById(jwtTokenDTO.getId(), idLimit);

        expenseLimit = limitMapper.updateFromDTO(expenseLimitDTO, expenseLimit);

        limitsRepository.save(expenseLimit);
    }

    public void deleteById(Long idLimit, Long idUser) {
        findById(idUser, idLimit);

        limitsRepository.deleteById(idLimit);
    }
}
