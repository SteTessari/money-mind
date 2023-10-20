package com.example.moneymind.dtos;

import com.example.moneymind.enums.FormaPagamento;
import com.example.moneymind.enums.StatusConta;
import com.example.moneymind.enums.TipoCategoria;
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
public class GastoEssencialFiltro implements Serializable {
    private String descricao;
    private String estabelecimento;
    private LocalDate vencimento;
    private LocalDate dataPagamento;
    private StatusConta statusConta = StatusConta.EM_ABERTO;
    private YearMonth mes;
    private BigDecimal valor;
    private FormaPagamento formaPagamento;
    private TipoCategoria tipoCategoria;

}
