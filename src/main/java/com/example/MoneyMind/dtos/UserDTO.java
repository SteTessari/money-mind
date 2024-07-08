package com.example.MoneyMind.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Informe um nome de usuário")
    private String username;
    @Email(message = "Por favor informe uma email válido")
    @NotBlank(message = "Informe um email")
    private String email;
    @NotBlank(message = "Informe a senha")
    @Size(min = 8, max = 20, message = "A senha deve conter 8 caracteres")
    private String password;
}
