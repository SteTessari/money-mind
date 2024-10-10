package com.example.MoneyMind.util.validations;

import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.entidades.Expense;
import com.example.MoneyMind.entidades.ExpenseLimit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ValidateEssencialExpenses {

    protected void validateLimit(List<Expense> gastos,
                                 ExpenseLimit limite, BigDecimal valor) {
        BigDecimal total = gastos.stream()
                .map(Expense::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (limite != null) {
            BigDecimal totalExpenses = total.add(valor);
            if (totalExpenses.compareTo(limite.getLimit()) > 0) {
                throw new MoneyMindException(HttpStatus.BAD_REQUEST, "The expense amount is greater than the defined limit.");
            }
        }
    }
}
