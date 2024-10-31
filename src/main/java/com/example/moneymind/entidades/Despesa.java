package com.example.moneymind.entidades;

import com.example.moneymind.enums.Status;
import com.example.moneymind.enums.TipoDespesa;
import com.example.moneymind.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Table(name = "despesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Despesa implements Serializable {
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

    @Column(name = "DATA_PAGAMENTO")
    private LocalDate dataPagamento;

    @Column(name = "DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS")
    private Status status = Status.EM_ABERTO;

    @Column(name = "DATA", nullable = false)
    private YearMonth data;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_PAGAMENTO", nullable = false)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_DESPESA", nullable = false)
    private TipoDespesa tipoDespesa;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

}
