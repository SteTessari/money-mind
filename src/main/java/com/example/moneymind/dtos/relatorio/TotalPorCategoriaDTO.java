package com.example.moneymind.dtos.relatorio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalPorCategoriaDTO implements Serializable {
    private String categoria;
    private BigDecimal valor;
}
