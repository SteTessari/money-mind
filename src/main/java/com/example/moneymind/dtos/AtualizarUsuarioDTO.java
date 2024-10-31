package com.example.moneymind.dtos;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarUsuarioDTO implements Serializable {
    private String username;

    @Email(message = "Por favor informe um email válido.")
    private String email;

    private String telefone;

    private String senhaAtual;
}
