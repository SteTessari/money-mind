package com.example.moneymind.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "receita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;
    @NotNull
    @Column(name = "MES")
    private YearMonth mes;
    @NotNull
    @Column(name = "SALARIO")
    private BigDecimal salario;
    @NotNull
    @Column(name = "CREDITO")
    private BigDecimal credito;
    @NotNull
    @Column(name = "EXTRA")
    private BigDecimal extra;

}
