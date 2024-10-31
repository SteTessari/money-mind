package com.example.moneymind.dtos.relatorio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioDTO implements Serializable {
    private String mes;
    private BigDecimal totalDespesasDebito;
    private BigDecimal totalDespesasCredito;
    private BigDecimal saldoTotalDebito;
    private List<TotalPorCategoriaDTO> totaisPorCategoria;

}
