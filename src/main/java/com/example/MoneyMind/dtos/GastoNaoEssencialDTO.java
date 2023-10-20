package com.example.moneymind.dtos;

import com.example.moneymind.enums.TipoCategoria;
import com.example.moneymind.enums.FormaPagamento;
import com.example.moneymind.enums.StatusConta;
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
public class GastoNaoEssencialDTO implements Serializable {
    @NotBlank
    private String descricao;
    private String estabelecimento;
    @NotNull
    private LocalDate vencimento;
    @NotNull
    private LocalDate dataPagamento;
    @NotNull
    private StatusConta statusConta = StatusConta.EM_ABERTO;
    @NotNull
    private YearMonth mes;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private FormaPagamento formaPagamento;
    @NotNull
    private TipoCategoria tipoCategoria;


}
