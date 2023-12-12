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
    @NotNull
    private String month;
    @NotNull
    private BigDecimal salary;
    @NotNull
    private BigDecimal credit;
    @NotNull
    private BigDecimal extraIncome;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
