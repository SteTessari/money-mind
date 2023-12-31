package com.example.MoneyMind.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;

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
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;
    @NotNull
    @Column(name = "MES", columnDefinition = "VARCHAR(7)")
    private String mes;
    @NotNull
    @Column(name = "SALARIO")
    private BigDecimal salario;
    @NotNull
    @Column(name = "CREDITO")
    private BigDecimal credito;
    @NotNull
    @Column(name = "EXTRA")
    private BigDecimal extra;

    public void setMes(YearMonth yearMonth) {
        this.mes = yearMonth.toString();
    }

    public YearMonth getMes() {
        return YearMonth.parse(mes);
    }
}
