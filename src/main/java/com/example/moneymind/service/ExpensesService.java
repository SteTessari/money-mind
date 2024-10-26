package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.ExpenseDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Expense;
import com.example.moneymind.entidades.ExpenseLimit;
import com.example.moneymind.enums.Status;
import com.example.moneymind.mapper.EssencialExpensesMapper;
import com.example.moneymind.repository.ExpenseRepository;
import com.example.moneymind.repository.LimitsRepository;
import com.example.moneymind.util.validations.ValidateEssencialExpenses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesService extends ValidateEssencialExpenses {

    private final LimitsRepository limitsRepository;
    private final ExpenseRepository expenseRepository;

    private static final EssencialExpensesMapper essencialExpensesMapper = EssencialExpensesMapper.INSTANCE;

    public void create(ExpenseDTO expenseDTO, JwtTokenDTO jwtTokenDTO) {
        if (expenseDTO.getInvoicePaymentDate() != null) {
            expenseDTO.setStatus(Status.PAGA);
        }

        if (expenseDTO.getInvoicePaymentDate() == null &&
                expenseDTO.getInvoiceDueDate().isBefore(LocalDate.now())) {
            expenseDTO.setStatus(Status.ATRASADA);
        }

        Expense expense = essencialExpensesMapper.toObject(expenseDTO);
        create(expense);
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
                .toList();
    }
}
