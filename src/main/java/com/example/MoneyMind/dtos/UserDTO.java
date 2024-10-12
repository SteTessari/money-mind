package com.example.MoneyMind.dtos;


import com.example.MoneyMind.anotacoes.ContainsCapitalLetter;
import com.example.MoneyMind.anotacoes.ContainsLowercaseLetter;
import com.example.MoneyMind.anotacoes.ContainsSpecialCharacter;
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
    @NotBlank(message = "Please enter a username.")
    private String username;

    @Email(message = "Please provide a valid email.")
    @NotBlank(message = "Please provide an email")
    private String email;

    @NotBlank(message = "Please enter password")
    @Size(min = 6, max = 20, message = "Password must contain 6 characters")
    @ContainsCapitalLetter(message = "The password must have at least 1 capital letter")
    @ContainsLowercaseLetter(message = "Password must have at least 1 lowercase letter")
    @ContainsSpecialCharacter(message = "Password must have at least 1 special character")
    private String password;

    private String cellPhone;
}
