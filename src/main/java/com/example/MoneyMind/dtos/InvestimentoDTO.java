package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.TipoInvestimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestimentoDTO implements Serializable {
    @NotBlank
    private String descricao;
    private YearMonth mes;
    private String estabelecimento;
    private TipoInvestimento tipoInvestimento;
    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataResgate;
    @NotNull
    private BigDecimal valorInicial;
    @NotNull
    private BigDecimal valorFinal;
}
