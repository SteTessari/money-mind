package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.CategoryType;
import com.example.MoneyMind.enums.FormPaymentType;
import com.example.MoneyMind.enums.Status;
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
import java.time.Month;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO implements Serializable {
    @NotNull(message = "Por favor informe o usuário")
    private Long idUser;

    @NotBlank(message = "Por favor informa a descrição da despesa")
    private String description;

    private String commercialPlace;

    @NotNull(message = "Por favor informe a data de vencimento da fatura")
    private LocalDate invoiceDueDate;

    private LocalDate invoicePaymentDate;

    @NotNull
    private Status status = Status.EM_ABERTO;
    @NotNull(message = "Por favor informe o mês")
    private Month month;

    @NotNull(message = "Por favor informe o valor da despesa")
    private BigDecimal value;

    @NotNull(message = "Por favor informe a forma de pagamento")
    private FormPaymentType formOfPayment;

    @NotNull(message = "Por favor informe a categoria")
    private CategoryType categoryType;

    @NotNull(message = "Por favor informe o tipo da despesa")
    private TypeFinancialExpense typeFinancialExpense;

}
