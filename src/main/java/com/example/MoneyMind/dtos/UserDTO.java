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
    @NotBlank(message = "Please enter a username.")
    private String username;
    @Email(message = "Please provide a valid email.")
    @NotBlank(message = "Please provide an email")
    private String email;
    @NotBlank(message = "Please enter password")
    @Size(min = 6, max = 20, message = "Password must contain 6 characters")
    private String password;

    private String cellPhone;
}
