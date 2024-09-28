package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.CategoryType;
import com.example.MoneyMind.enums.FormPaymentType;
import com.example.MoneyMind.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseFilter implements Serializable {
    private String description;
    private String commercialPlace;
    private LocalDate invoiceDueDate;
    private LocalDate invoicePaymentDate;
    private Status status = Status.EM_ABERTO;
    @NotNull
    private Month month;
    private BigDecimal value;
    private FormPaymentType formOfPayment;
    private CategoryType categoryType;

}
