package com.example.moneymind.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Month;

@Entity
@Table(name = "financial_income")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "ID_USER", nullable = false)
    private Long idUser;

    @NotNull
    @Column(name = "MONTH", nullable = false)
    private Month month;

    @NotNull
    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "WAGE", nullable = false)
    private BigDecimal wage;

    @NotNull
    @Column(name = "CREDIT_CAPACITY", nullable = false)
    private BigDecimal creditCapacity;

    @NotNull
    @Column(name = "EXTRA_INCOME", nullable = false)
    private BigDecimal extraIncome;

}
