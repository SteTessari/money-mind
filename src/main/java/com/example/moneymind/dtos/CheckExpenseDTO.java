package com.example.moneymind.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckExpenseDTO implements Serializable {
    @NotNull(message = "Please enter the month")
    private String month;

    @NotNull(message = "Please inform the amount of the expense")
    private BigDecimal value;

    @NotNull(message = "Please enter the category")
    private Long idCategory;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }

    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
