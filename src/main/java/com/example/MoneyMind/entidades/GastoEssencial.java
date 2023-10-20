package com.example.MoneyMind.entidades;

import com.example.MoneyMind.enums.FormaPagamento;
import com.example.MoneyMind.enums.StatusConta;
import com.example.MoneyMind.enums.TipoCategoria;
import jakarta.persistence.*;
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

@Entity
@Table(name = "gastos_essenciais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastoEssencial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
//    @Column(name = "ID_USUARIO", nullable = false)
//    private Long idUsuario;
    @NotBlank
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ESTABELECIMENTO")
    private String estabelecimento;
    @NotNull
    @Column(name = "VENCIMENTO")
    private LocalDate vencimento;
    @NotNull
    @Column(name = "DATA_PAGAMENTO")
    private LocalDate dataPagamento;
    @NotNull
    @Column(name = "STATUS_CONTA")
    private StatusConta statusConta = StatusConta.EM_ABERTO;
    @NotNull
    @Column(name = "MES", columnDefinition = "VARCHAR(7)")
    private String mes;
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @NotNull
    @Column(name = "FORMA_PAGAMENTO")
    private FormaPagamento formaPagamento;
    @NotNull
    @Column(name = "TIPO_CATEGORIA")
    private TipoCategoria tipoCategoria;

    public void setMes(YearMonth yearMonth) {
        this.mes = yearMonth.toString();
    }
    public YearMonth getMes() {
        return YearMonth.parse(mes);
    }

}
