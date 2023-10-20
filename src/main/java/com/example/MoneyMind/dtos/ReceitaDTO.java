package com.example.MoneyMind.dtos;

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
public class ReceitaDTO implements Serializable {
    @NotNull
    private String mes;
    @NotNull
    private BigDecimal salario;
    @NotNull
    private BigDecimal credito;
    @NotNull
    private BigDecimal extra;

    public void setMes(YearMonth yearMonth) {
        this.mes = yearMonth.toString();
    }
    public YearMonth getMes() {
        return YearMonth.parse(mes);
    }
}
