package com.example.moneymind.util.validations;

import com.example.moneymind.dtos.UserDTO;

import java.util.regex.Pattern;

public class ValidateUserService {

    protected static boolean isSenhaValida(UserDTO userDTO) {
        String regexSenhaValida = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
        return Pattern.matches(regexSenhaValida, userDTO.getPassword());
    }
}
