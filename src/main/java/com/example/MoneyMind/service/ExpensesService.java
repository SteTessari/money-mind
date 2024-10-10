package com.example.MoneyMind.service;

import com.example.MoneyMind.dtos.ExpenseDTO;
import com.example.MoneyMind.entidades.Expense;
import com.example.MoneyMind.entidades.ExpenseLimit;
import com.example.MoneyMind.enums.Status;
import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.mapper.EssencialExpensesMapper;
import com.example.MoneyMind.repository.ExpenseRepository;
import com.example.MoneyMind.repository.LimitsRepository;
import com.example.MoneyMind.util.validations.ValidateEssencialExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensesService extends ValidateEssencialExpenses {

    @Autowired
    private LimitsRepository limitsRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    private final EssencialExpensesMapper essencialExpensesMapper = EssencialExpensesMapper.INSTANCE;

    public void create(ExpenseDTO expenseDTO) {
        if (expenseDTO.getInvoicePaymentDate() != null) {
            expenseDTO.setStatus(Status.PAGA);
        }

        if (expenseDTO.getInvoicePaymentDate() == null &&
                expenseDTO.getInvoiceDueDate().isBefore(LocalDate.now())) {
            expenseDTO.setStatus(Status.ATRASADA);
        }

        Expense expense = essencialExpensesMapper.toObject(expenseDTO);
        treatmentsBeforeInserting(expense);
    }

    public void update(Long idGastoEssencial, ExpenseDTO expenseDTO) {
        Expense expense = findById(idGastoEssencial);

        Expense expenseUpdated = essencialExpensesMapper.updateFromDTO(
                expenseDTO, expense);

        create(expenseUpdated);
    }

    public ExpenseDTO findDTOById(Long idEssentialExpenses) {
        Expense expense = expenseRepository.findById(idEssentialExpenses)
                .orElseThrow(() -> new MoneyMindException(
                        HttpStatus.NO_CONTENT,
                        "No result found."));

        return essencialExpensesMapper.toDTO(expense);
    }

    public Page<ExpenseDTO> findAll(Long idUser, Pageable pageable) {

        Page<Expense> expenses = expenseRepository.findByIdUser(idUser, pageable);
        return expenses.map(essencialExpensesMapper::toDTO);
    }

    private void create(Expense expense) {
        expenseRepository.save(expense);
    }

    private void treatmentsBeforeInserting(Expense expense) {
        BigDecimal value = expense.getValue();

        List<Expense> expenses = expenseRepository
                .findByMonth(expense.getMonth());

        ExpenseLimit expenseLimit = limitsRepository.findByMonth(YearMonth.now().toString())
                .orElse(null);

        validateLimit(expenses, expenseLimit, value);
        create(expense);
    }

    private Expense findById(Long idGastoEssencial) {
        return expenseRepository.findById(idGastoEssencial)
                .orElseThrow(() -> new MoneyMindException(
                        HttpStatus.NO_CONTENT,
                        "No result found."));
    }

    public List<ExpenseDTO> filter(String description) {
        return expenseRepository.findByDescription(description)
                .stream()
                .map(essencialExpensesMapper::toDTO)
                .collect(Collectors.toList());
    }
}
