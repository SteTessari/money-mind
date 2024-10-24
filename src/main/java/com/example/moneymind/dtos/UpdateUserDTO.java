package com.example.moneymind.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO implements Serializable {
    private String username;

    @Email(message = "Please provide a valid email.")
    private String email;

    private String cellPhone;

    private String currentPassword;
}
