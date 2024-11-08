package com.example.moneymind.dtos;

import com.example.moneymind.config.exception.MoneyMindException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDTO implements Serializable {
    @NotBlank(message = "Por favor informe a data")
    private String data;

    @NotNull(message = "Por favor informe o salário")
    private BigDecimal salario;
    private BigDecimal rendaExtra;

    public String getData() {
        try{
            YearMonth.parse(data);
            return data;
        }catch (DateTimeParseException e){
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Data inválida.");
        }
    }
}
