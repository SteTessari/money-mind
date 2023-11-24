package com.example.MoneyMind.dtos;

import com.example.MoneyMind.enums.FormaPagamento;
import com.example.MoneyMind.enums.StatusConta;
import com.example.MoneyMind.enums.TipoCategoria;
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
public class GastoEssencialDTO implements Serializable {
    @NotBlank
    private String descricao;
    private String estabelecimento;
    @NotNull
    private LocalDate vencimento;
    private LocalDate dataPagamento;
    @NotNull
    private StatusConta statusConta = StatusConta.EM_ABERTO;
    @NotNull
    private String mes;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private FormaPagamento formaPagamento;
    @NotNull
    private TipoCategoria tipoCategoria;

    public void setMes(YearMonth yearMonth) {
        this.mes = yearMonth.toString();
    }
    public YearMonth getMes() {
        return YearMonth.parse(mes);
    }
}
