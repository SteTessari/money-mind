package com.example.moneymind.dtos;

import jakarta.validation.constraints.NotBlank;
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
public class ExpenseLimitDTO implements Serializable {
    @NotNull
    private Long idCategory;

    @NotBlank
    private String month;

    @NotNull
    private BigDecimal limit;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }

    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
