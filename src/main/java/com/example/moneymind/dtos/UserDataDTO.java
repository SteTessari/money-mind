package com.example.moneymind.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO implements Serializable {
    private String username;

    private String email;

    private String cellPhone;
}
