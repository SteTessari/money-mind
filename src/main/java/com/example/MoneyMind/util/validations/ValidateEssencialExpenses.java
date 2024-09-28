package com.example.MoneyMind.util.validations;

import com.example.MoneyMind.entidades.Expense;
import com.example.MoneyMind.entidades.ExpenseLimit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ValidateEssencialExpenses {

    protected void validarLimite(List<Expense> gastos,
                                 ExpenseLimit limite, BigDecimal valor) {
        BigDecimal total = gastos.stream()
                .map(Expense::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (limite != null) {
            BigDecimal totalGasto = total.add(valor);
            if (totalGasto.compareTo(limite.getLimit()) > 0) {
                throw new RuntimeException("O valor do gasto é maior do que o limite definido.");
            }
        }
    }
}
