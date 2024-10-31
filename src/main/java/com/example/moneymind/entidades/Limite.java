package com.example.moneymind.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "limite", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_USUARIO", "ID_CATEGORIA"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Limite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

    @Column(name = "DATA")
    private YearMonth data;

    @Column(name = "limite")
    private BigDecimal limite;
}
