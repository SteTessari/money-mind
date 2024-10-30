package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.ExpenseLimitDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Expense;
import com.example.moneymind.entidades.ExpenseLimit;
import com.example.moneymind.mapper.LimitMapper;
import com.example.moneymind.repository.LimitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitsService {
    private final LimitsRepository limitsRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ExpensesService expensesService;
    private final LimitMapper limitMapper = LimitMapper.INSTANCE;

    public void create(ExpenseLimit expenseLimit, Long idUser) {
        userService.findById(expenseLimit.getIdUser());

        Optional<ExpenseLimit> expenseLimitOptional = limitsRepository.findByIdUserAndIdCategory(idUser, expenseLimit.getIdCategory());
        if (expenseLimitOptional.isPresent()) {
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Limit already registered for the informed category.");
        }

        categoryService.validateUserCategory(expenseLimit.getIdCategory(), expenseLimit.getIdUser());

        limitsRepository.save(expenseLimit);
    }

    public ExpenseLimit findById(Long idUser, Long idLimit) {
        Optional<ExpenseLimit> limitOptional = limitsRepository.findByIdAndIdUser(idLimit, idUser);

        if (limitOptional.isPresent()) {
            return limitOptional.get();
        } else {
            throw new MoneyMindException(HttpStatus.NOT_FOUND, "Limit not found.");
        }
    }

    public void editLimit(Long idLimit, ExpenseLimitDTO expenseLimitDTO, JwtTokenDTO jwtTokenDTO) {
        ExpenseLimit expenseLimit = findById(jwtTokenDTO.getId(), idLimit);

        expenseLimit = limitMapper.updateFromDTO(expenseLimitDTO, expenseLimit);

        limitsRepository.save(expenseLimit);
    }

    public void deleteById(Long idLimit, Long idUser) {
        findById(idUser, idLimit);

        limitsRepository.deleteById(idLimit);
    }

    public String checkLimitBeforeUpdatingLimit(Long idLimit, ExpenseLimitDTO expenseLimitDTO, Long idUser) {
        findById(idUser, idLimit);

        List<Expense> expenses = expensesService.getExpensesByIdUserMonthAndIdCategory(
                expenseLimitDTO.getMonth().toString(), expenseLimitDTO.getIdCategory(), idUser);

        BigDecimal totalExpenses = expenses.stream().map(Expense::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (expenseLimitDTO.getLimit().compareTo(totalExpenses) < 0) {
            return "The new limit defined is less than the total expenses. This will be reflected in the report, do you want to continue anyway?";
        } else return null;
    }
}
