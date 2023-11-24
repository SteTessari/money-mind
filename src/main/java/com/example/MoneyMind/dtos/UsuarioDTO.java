package com.example.MoneyMind.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    @Email
    @NotNull
    private String email;
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 8, max = 8, message = "A senha deve conter 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "A senha deve conter pelo menos 1 letra maiúscula, 1 letra minúscula, 1 dígito e 1 caractere especial")
    private String senha;
    @NotBlank(message = "Campo obrigatório")
    private String confirmacaoSenha;
    private String idGoogle;

}
