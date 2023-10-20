package com.example.MoneyMind.entidades;

import com.example.MoneyMind.enums.TipoCategoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Limites implements Serializable {
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
    @Column(name = "LIMITE")
    private BigDecimal limite;
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
