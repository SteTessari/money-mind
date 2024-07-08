package com.example.MoneyMind.entidades;

import com.example.MoneyMind.enums.FormPaymentType;
import com.example.MoneyMind.enums.AccountStatus;
import com.example.MoneyMind.enums.CategoryType;
import com.example.MoneyMind.enums.TypeFinancialExpense;
import jakarta.persistence.*;
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

@Entity
@Table(name = "essential_expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EssentialExpenses implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "ID_USER", nullable = false)
    private Long idUser;
    @NotBlank
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COMMERCIAL_PLACE")
    private String commercialPlace;
    @NotNull
    @Column(name = "INVOICE_PAYMENT")
    private LocalDate invoicePayment;
    @NotNull
    @Column(name = "INVOICE_PAYMENT_DATE")
    private LocalDate invoicePaymentDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS")
    private AccountStatus accountStatus = AccountStatus.EM_ABERTO;
    @NotNull
    @Column(name = "MONTH", columnDefinition = "VARCHAR(7)")
    private String month;
    @NotNull
    @Column(name = "VALUE")
    private BigDecimal value;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FORM_PAYMENT")
    private FormPaymentType formOfPayment;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY_TYPE")
    private CategoryType categoryType;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_FINANCIAL_EXPENSE")
    private TypeFinancialExpense typeFinancialExpense;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }

}
