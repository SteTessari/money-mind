package com.example.moneymind.entidades;

import com.example.moneymind.enums.TipoInvestimento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "ESTABELECIMENTO")
    private String estabelecimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private TipoInvestimento tipoInvestimento;

    @NotNull
    @Column(name = "DATA_APLICACAO", nullable = false)
    private LocalDate dataAplicacao;

    @NotNull
    @Column(name = "DATA_RESGATE", nullable = false)
    private LocalDate dataResgate;

    @NotNull
    @Column(name = "VALOR_INICIAL", nullable = false)
    private BigDecimal valorInicial;

    @NotNull
    @Column(name = "VALOR_RESGATADO")
    private BigDecimal valorResgatado;
}
