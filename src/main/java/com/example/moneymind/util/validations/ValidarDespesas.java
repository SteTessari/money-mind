package com.example.moneymind.util.validations;

import com.example.moneymind.entidades.Despesa;
import com.example.moneymind.entidades.Limite;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ValidarDespesas {

    protected String validarLimite(List<Despesa> gastos,
                                   Limite limite, BigDecimal valor) {
        BigDecimal total = gastos.stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (limite != null) {
            BigDecimal totalDespesas = total.add(valor);
            if (totalDespesas.compareTo(limite.getLimite()) > 0) {
                return "Limite atingido, deseja continuar mesmo assim?.";
            }
        }

        return null;
    }
}
