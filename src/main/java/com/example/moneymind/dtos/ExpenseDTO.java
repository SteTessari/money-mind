package com.example.moneymind.dtos;

import com.example.moneymind.enums.FormPaymentType;
import com.example.moneymind.enums.Status;
import com.example.moneymind.enums.TypeFinancialExpense;
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
public class ExpenseDTO implements Serializable {

    @NotBlank(message = "Please provide a description of the expense")
    private String description;

    private String commercialPlace;

    @NotNull(message = "Please provide the due date of the invoice")
    private LocalDate invoiceDueDate;

    private LocalDate invoicePaymentDate;

    @NotNull
    private Status status = Status.EM_ABERTO;
    @NotNull(message = "Please enter the month")
    private String month;

    @NotNull(message = "Please inform the amount of the expense")
    private BigDecimal value;

    @NotNull(message = "Please inform the payment method")
    private FormPaymentType formOfPayment;

    @NotNull(message = "Please enter the category")
    private Long idCategory;

    @NotNull(message = "Please enter the type of expense")
    private TypeFinancialExpense typeFinancialExpense;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }

    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
