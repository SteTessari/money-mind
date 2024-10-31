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
public class LimiteDTO implements Serializable {
    @NotNull(message = "Por favor informe a categoria")
    private Long idCategoria;

    @NotBlank(message = "Por favor informe a data")
    private YearMonth data;


    @NotNull(message = "Por favor informe o limite para a categoria")
    private BigDecimal limite;

}
