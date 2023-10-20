package com.example.MoneyMind.util.validacao;

import com.example.moneymind.entidades.GastoEssencial;
import com.example.moneymind.entidades.Limites;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ValidacaoGastoEssencial {

    protected void validarLimite(List<GastoEssencial> gastos,
                                 Limites limite, BigDecimal valor) {
        BigDecimal total = gastos.stream()
                .map(GastoEssencial::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (limite != null) {
            BigDecimal totalGasto = total.add(valor);
            if (totalGasto.compareTo(limite.getLimite()) > 0) {
                throw new RuntimeException("O valor do gasto é maior do que o limite definido.");
            }
        }
    }
}
