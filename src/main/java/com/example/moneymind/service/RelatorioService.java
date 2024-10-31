package com.example.moneymind.service;

import com.example.moneymind.repository.DespesaRepository;
import com.example.moneymind.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final DespesaRepository despesaRepository;
    private final ReceitaRepository receitaRepository;

    public BigDecimal buscarSaldoTotal(Long idUsuario) {
        YearMonth data = YearMonth.now();

        BigDecimal totalDespesas = Optional.ofNullable(despesaRepository.findTotalExpenseByMonthAndYearAndidUsuario(data, idUsuario))
                .orElse(BigDecimal.ZERO);

        BigDecimal totalSalario = Optional.ofNullable(receitaRepository.findSalarioByDataAndUsuario_Id(data, idUsuario))
                .orElse(BigDecimal.ZERO);

        return totalSalario.subtract(totalDespesas);
    }

}
