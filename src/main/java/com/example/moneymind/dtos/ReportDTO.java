package com.example.moneymind.dtos;

import com.example.moneymind.enums.CategoryType;
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
    private String month;
    private BigDecimal totalExpensesDebit;
    private BigDecimal totalExpensesCredit;
    private BigDecimal debitFinancialBalance;
    private BigDecimal creditFinancialBalance;
    private BigDecimal totalByCategory;
    private CategoryType categoryType;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
