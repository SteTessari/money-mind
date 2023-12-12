package com.example.MoneyMind.configs.security.config.token;

import org.springframework.stereotype.Service;

@Service
public interface JwtProviderUtils {

    JwtTokenDecodeDTO getPayload(String token);

}
