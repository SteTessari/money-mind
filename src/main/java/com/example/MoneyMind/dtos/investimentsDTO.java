package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.InvestmentType;
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
public class investimentsDTO implements Serializable {
    @NotBlank
    private String description;
    @NotNull
    private String month;
    private String placeInvest;
    private InvestmentType investmentType;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate investmentRedemption;
    @NotNull
    private BigDecimal InitialValue;
    @NotNull
    private BigDecimal finalValeu;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
