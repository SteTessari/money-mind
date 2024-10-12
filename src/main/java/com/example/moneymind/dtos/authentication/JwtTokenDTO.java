package com.example.moneymind.dtos.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class JwtTokenDTO implements Serializable {
    private Long id;
    private String email;
    private String username;
}
