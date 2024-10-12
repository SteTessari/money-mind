package com.example.moneymind.entidades;

import com.example.moneymind.enums.FormPaymentType;
import com.example.moneymind.enums.Status;
import com.example.moneymind.enums.TypeFinancialExpense;
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
import java.time.Month;

@Entity
@Table(name = "expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "ID_USER", nullable = false)
    private Long idUser;

    @NotBlank
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "COMMERCIAL_PLACE")
    private String commercialPlace;

    @Column(name = "INVOICE_PAYMENT_DATE")
    private LocalDate invoicePaymentDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS")
    private Status status = Status.EM_ABERTO;
    @NotNull
    @Column(name = "MONTH", nullable = false)
    private Month month;

    @NotNull
    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FORM_PAYMENT", nullable = false)
    private FormPaymentType formOfPayment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_FINANCIAL_EXPENSE", nullable = false)
    private TypeFinancialExpense typeFinancialExpense;


}
