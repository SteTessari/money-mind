package com.example.moneymind.dtos;

import com.example.moneymind.enums.InvestmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotBlank(message = "Please provide description")
    private String description;
    @NotNull(message = "Please enter the month")
    private String month;
    private String placeInvest;
    @NotNull(message = "Please inform the type of investment")
    private InvestmentType investmentType;
    @NotNull(message = "Please provide the date of application")
    private LocalDate startDate;
    @NotNull(message = "Please provide the redemption date")
    private LocalDate investmentRedemption;

    @PositiveOrZero
    @NotNull(message = "Please inform the amount applied")
    private BigDecimal InitialValue;

    private BigDecimal finalValeu;

    public void setMonth(YearMonth yearMonth) {
        this.month = yearMonth.toString();
    }
    public YearMonth getMonth() {
        return YearMonth.parse(month);
    }
}
