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
    @NotNull(message = "Por favor informe o usuário")
    private Long idUser;
    @NotBlank(message = "Por favor informa a descrição da despesa")
    private String description;
    private String commercialPlace;
    @NotNull(message = "Por favor informe a data de vencimento da fatura")
    private LocalDate invoiceDueDate;
    private LocalDate invoicePaymentDate;
    @NotNull
    private AccountStatus accountStatus = AccountStatus.EM_ABERTO;
    @NotNull(message = "Por favor informe o mês")
    private String month;
    @NotNull(message = "Por favor informe o valor da despesa")
    private BigDecimal value;
    @NotNull(message = "Por favor informe a forma de pagamento")
    private FormPaymentType formOfPayment;
    @NotNull(message = "Por favor informe a categoria")
    private CategoryType categoryType;
    @NotNull(message = "Por favor informe o tipo da despesa")
    private TypeFinancialExpense typeFinancialExpense;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
