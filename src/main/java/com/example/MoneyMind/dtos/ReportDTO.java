package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.CategoryType;
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
public class ReportDTO implements Serializable {
    @NotNull
    private String month;
    @NotNull
    private BigDecimal TotalExpensesDebit;
    @NotNull
    private BigDecimal totalExpensesCredit;
    @NotNull
    private BigDecimal debitFinancialBalance;
    @NotNull
    private BigDecimal creditFinancialBalance;
    @NotNull
    private BigDecimal totalByCategory;
    @NotNull
    private CategoryType categoryType;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
