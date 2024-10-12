package com.example.moneymind.service;

import com.example.moneymind.repository.ExpenseRepository;
import com.example.moneymind.repository.FinancialIncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ExpenseRepository expenseRepository;
    private final FinancialIncomeRepository financialIncomeRepository;

    public BigDecimal findTotalBalance(Long idUser) {
        Month month = LocalDate.now().getMonth();
        int year = LocalDate.now().getYear();

        BigDecimal totalExpenses = Optional.ofNullable(expenseRepository.findTotalExpenseByMonthAndYearAndIdUser(month, year, idUser))
                .orElse(BigDecimal.ZERO);

        BigDecimal totalWage = Optional.ofNullable(financialIncomeRepository.findWageByIdUserAndMonth(idUser, year, month))
                .orElse(BigDecimal.ZERO);

        return totalWage.subtract(totalExpenses);
    }

}
