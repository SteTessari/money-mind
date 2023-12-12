package com.example.MoneyMind.entidades;

import com.example.MoneyMind.enums.InvestmentType;
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
@Table(name = "investiments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investiments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "ID_USER", nullable = false)
    private Long idUser;
    @NotBlank
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COMMERCIAL_PLACE")
    private String commercialPlace;
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private InvestmentType type;
    @NotNull
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @NotNull
    @Column(name = "INVESTIMENT_REDEMPTION")
    private LocalDate investmentRedemption;
    @NotNull
    @Column(name = "INITIAL_VALUE")
    private BigDecimal initialValue;
    @NotNull
    @Column(name = "FINAL_VALUE")
    private BigDecimal finalValue;
}
