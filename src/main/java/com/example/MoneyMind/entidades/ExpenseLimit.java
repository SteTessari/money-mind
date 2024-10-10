package com.example.MoneyMind.entidades;

import com.example.MoneyMind.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "expense_limit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseLimit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @NotNull
    @Column(name = "ID_USER", nullable = false)
    private Long idUser;
    @NotNull
    @Column(name = "ID_CATEGORY", nullable = false)
    private Long idCategory;

    @Column(name = "MONTH", columnDefinition = "VARCHAR(7)")
    private String month;
    @NotNull
    @Column(name = "EXPENSE_LIMIT")
    private BigDecimal limit;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }

    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
