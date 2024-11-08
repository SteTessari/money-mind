package com.example.moneymind.entidades;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "DATA")
    private String data;

    @Column(name = "SALARIO", nullable = false)
    private BigDecimal salario;

    @Column(name = "RENDA_EXTRA", nullable = false)
    private BigDecimal rendaExtra;

}
