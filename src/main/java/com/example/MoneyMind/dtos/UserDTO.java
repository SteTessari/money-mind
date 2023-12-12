package com.example.MoneyMind.dtos;


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
    @NotBlank(message = "Username required")
    private String username;
    @NotBlank(message = "Password required")
    @Size(min = 8, max = 8, message = "Password must contain 8 characters")
    private String password;
}
