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
public class UserDTO implements Serializable {
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
