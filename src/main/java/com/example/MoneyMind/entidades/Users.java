package com.example.MoneyMind.entidades;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @NotBlank
    @Column(name = "USERNAME", unique = true)
    private String username;
    @Email(message = "Por favor informe uma email válido")
    @Column(name = "EMAIL")
    private String email;
    @NotBlank
    @Column(name = "PASSWORD")
    private String password;



}
