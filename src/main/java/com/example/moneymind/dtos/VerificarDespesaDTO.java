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
public class VerificarDespesaDTO implements Serializable {
    @NotNull(message = "Por favor informe o mês")
    private YearMonth data;

    @NotNull(message = "Por favor informe o valor")
    private BigDecimal valor;

    @NotNull(message = "por favor informe a categoria")
    private Long idCategoria;

}
