package com.example.MoneyMind.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Email
    @NotNull
    @Column(name = "EMAIL", columnDefinition = "VARCHAR(100) NOT NULL")
    private String email;
    @Column(name = "SENHA", columnDefinition = "VARCHAR(32) NOT NULL")
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 8, max = 8, message = "A senha deve conter 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "A senha deve conter pelo menos 1 letra maiúscula, 1 letra minúscula, 1 dígito e 1 caractere especial")
    private String senha;
    @Column(name = "CONFIRMACAO_SENHA")
    @NotBlank(message = "Campo obrigatório")
    private String confirmacaoSenha;
    @Column(name = "ID_GOOGLE")
    private String idGoogle;
    @Column(name = "ATIVO", columnDefinition = "BIT(1) DEFAULT 1")
    private boolean ativo;
    @Column(name = "DATA_CRIACAO", columnDefinition = "DATETIME NOT NULL")
    private LocalDate dataCriacao;
    @Column(name = "DATA_ALTERACAO", columnDefinition = "DATETIME NOT NULL")
    private LocalDate dataAlteracao;


}
