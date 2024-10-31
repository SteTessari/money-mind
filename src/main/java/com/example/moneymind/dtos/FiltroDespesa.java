package com.example.moneymind.dtos;

import com.example.moneymind.enums.Status;
import com.example.moneymind.enums.TipoDespesa;
import com.example.moneymind.enums.TipoPagamento;
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
public class FiltroDespesa implements Serializable {
    private String descricao;
    private String estabelecimento;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Status status = Status.EM_ABERTO;
    @NotNull
    private YearMonth data;

    private BigDecimal valor;
    private TipoPagamento formaPagamento;
    private TipoDespesa tipoDespesa;
    private Long idCategoria;

}
