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
public class ReceitaDTO implements Serializable {
    @NotNull(message = "Por favor informe a data")
    private YearMonth data;

    @NotNull(message = "Por favor informe o salário")
    private BigDecimal salario;
    private BigDecimal rendaExtra;
}
