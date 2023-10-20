package com.example.moneymind.dtos;

import com.example.moneymind.enums.TipoCategoria;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioDTO implements Serializable {
    @NotNull
    private YearMonth mes;
    @NotNull
    private BigDecimal totalDespesasDebito;
    @NotNull
    private BigDecimal totalDespesasCredito;
    @NotNull
    private BigDecimal saldoDebito;
    @NotNull
    private BigDecimal saldoCredito;
    @NotNull
    private BigDecimal totalPorCategoria;
    @NotNull
    private TipoCategoria tipoCategoria;


}
