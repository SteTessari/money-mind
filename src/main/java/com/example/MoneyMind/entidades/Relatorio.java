package com.example.MoneyMind.entidades;

import com.example.MoneyMind.enums.TipoCategoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "relatorio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    //    @Column(name = "ID_USUARIO", nullable = false)
//    private Long idUsuario;
    @NotNull
    @Column(name = "MES", columnDefinition = "VARCHAR(7)")
    private String mes;
    @NotNull
    @Column(name = "TOTAL_DESPESAS_DEBITO")
    private BigDecimal totalDespesasDebito;
    @NotNull
    @Column(name = "TOTAL_DESPESAS_CREDITO")
    private BigDecimal totalDespesasCredito;
    @NotNull
    @Column(name = "SALDO_DEBITO")
    private BigDecimal saldoDebito;
    @NotNull
    @Column(name = "SALDO_CREDITO")
    private BigDecimal saldoCredito;
    @NotNull
    @Column(name = "TOTAL_POR_CATEGORIA")
    private BigDecimal totalPorCategoria;
    @NotNull
    @Column(name = "CATEGORIA")
    private TipoCategoria tipoCategoria;

    public void setMes(YearMonth yearMonth) {
        this.mes = yearMonth.toString();
    }
    public YearMonth getMes() {
        return YearMonth.parse(mes);
    }
}
