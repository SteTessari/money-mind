package com.example.MoneyMind.util.validations;

import com.example.MoneyMind.entidades.EssentialExpenses;
import com.example.MoneyMind.entidades.Limit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ValidateEssencialExpenses {

    protected void validarLimite(List<EssentialExpenses> gastos,
                                 Limit limite, BigDecimal valor) {
        BigDecimal total = gastos.stream()
                .map(EssentialExpenses::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (limite != null) {
            BigDecimal totalGasto = total.add(valor);
            if (totalGasto.compareTo(limite.getLimit()) > 0) {
                throw new RuntimeException("O valor do gasto é maior do que o limite definido.");
            }
        }
    }
}
