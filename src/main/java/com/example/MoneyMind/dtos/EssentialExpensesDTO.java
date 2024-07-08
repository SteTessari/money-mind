package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.CategoryType;
import com.example.MoneyMind.enums.FormPaymentType;
import com.example.MoneyMind.enums.AccountStatus;
import com.example.MoneyMind.enums.TypeFinancialExpense;
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
public class EssentialExpensesDTO implements Serializable {
    @NotBlank
    private String description;
    private String commercialPlace;
    @NotNull
    private LocalDate invoiceDueDate;
    private LocalDate invoicePaymentDate;
    @NotNull
    private AccountStatus accountStatus = AccountStatus.EM_ABERTO;
    @NotNull
    private String month;
    @NotNull
    private BigDecimal value;
    @NotNull
    private FormPaymentType formOfPayment;
    @NotNull
    private CategoryType categoryType;
    @NotNull
    private TypeFinancialExpense typeFinancialExpense;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
