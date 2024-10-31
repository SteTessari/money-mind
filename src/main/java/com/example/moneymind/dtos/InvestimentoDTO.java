package com.example.moneymind.dtos;

import com.example.moneymind.enums.TipoInvestimento;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestimentoDTO implements Serializable {
    @NotBlank(message = "Por favor informe a descrição")
    private String descricao;

    private String localInvestido;

    @NotNull(message = "Por favor informe o tipo de investimento")
    private TipoInvestimento tipoInvestimento;

    @NotNull(message = "Por favor informe a data da aplicação")
    private LocalDate dataAplicacao;

    @NotNull(message = "Por favor informe a data de resgate do investimento")
    private LocalDate dataResgate;

    @PositiveOrZero
    @NotNull(message = "Por favor informe o valor aplicado")
    private BigDecimal valorAplicado;

    private BigDecimal valorResgatado;
}
