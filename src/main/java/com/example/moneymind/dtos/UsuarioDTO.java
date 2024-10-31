package com.example.moneymind.dtos;


import com.example.moneymind.anotacoes.ContainsCapitalLetter;
import com.example.moneymind.anotacoes.ContainsLowercaseLetter;
import com.example.moneymind.anotacoes.ContainsSpecialCharacter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private String username;

    @Email(message = "Por favor informe um email válido.")
    @NotBlank(message = "Por favor informe um email")
    private String email;

    @NotBlank(message = "Por favor informe a senha")
    @Size(min = 6, max = 20, message = "A senha deve conter no mínimo 6 caracteres")
    @ContainsCapitalLetter(message = "A senha deve conter ao menos 1 letra maiúscula")
    @ContainsLowercaseLetter(message = "A senha deve conter ao menos 1 letra minúscula")
    @ContainsSpecialCharacter(message = "A senha deve conter ao menos 1 caracter especial")
    private String senha;

    private String telefone;
}
