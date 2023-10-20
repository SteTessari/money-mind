package com.example.moneymind.entidades;

import com.example.moneymind.enums.TipoInvestimento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investimentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;
    @NotBlank
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ESTABELECIMENTO")
    private String estabelecimento;
    @Column(name = "TIPO_INVESTIMENTO")
    private TipoInvestimento tipoInvestimento;
    @NotNull
    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;
    @NotNull
    @Column(name = "DATA_RESGATE")
    private LocalDate dataResgate;
    @NotNull
    @Column(name = "VALOR_INICIAL")
    private BigDecimal valorInicial;
    @NotNull
    @Column(name = "VALOR_FINAL")
    private BigDecimal valorFinal;
}
