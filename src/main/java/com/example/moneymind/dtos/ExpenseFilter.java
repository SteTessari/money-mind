package com.example.moneymind.dtos;

import com.example.moneymind.enums.CategoryType;
import com.example.moneymind.enums.FormPaymentType;
import com.example.moneymind.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

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
