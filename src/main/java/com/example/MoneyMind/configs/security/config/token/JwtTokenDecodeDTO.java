package com.example.MoneyMind.configs.security.config.token;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenDecodeDTO implements Serializable {
    private String roles;
    private Long idUser;
    private Long idSession;

}
