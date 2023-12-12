package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.CategoryType;
import com.example.MoneyMind.enums.FormPaymentType;
import com.example.MoneyMind.enums.AccountStatus;
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
public class EssentialExpensesFilter implements Serializable {
    private String description;
    private String commercialPlace;
    private LocalDate invoiceDueDate;
    private LocalDate invoicePaymentDate;
    private AccountStatus accountStatus = AccountStatus.EM_ABERTO;
    @NotNull
    private String month;
    private BigDecimal value;
    private FormPaymentType formOfPayment;
    private CategoryType categoryType;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }

    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
