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
public class FinancialIncomeDTO implements Serializable {
    @NotNull(message = "Please inform the user")
    private Long idUsuario;
    @NotNull(message = "Please enter the month")
    private String month;
    @NotNull(message = "Please provide wage")
    private BigDecimal wage;
    private BigDecimal credit;
    private BigDecimal extraIncome;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
