package com.example.MoneyMind.entidades;

import com.example.MoneyMind.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "ID_USER", nullable = false)
    private Long idUser;

    @NotNull
    @Column(name = "MONTH", columnDefinition = "VARCHAR(7)")
    private String month;

    @NotNull
    @Column(name = "DEBIT_TOTAL_EXPENSES")
    private BigDecimal debitTotalExpenses;

    @NotNull
    @Column(name = "CREDIT_TOTAL_EXPENSES")
    private BigDecimal creditTotalExpenses;

    @NotNull
    @Column(name = "DEBIT_BALANCE")
    private BigDecimal debitBalance;

    @NotNull
    @Column(name = "CREDIT_BALANCE")
    private BigDecimal creditBalance;

    @NotNull
    @Column(name = "TOTAL_FOR_CATEGORY")
    private BigDecimal totalForCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private CategoryType categoryType;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
