package com.example.moneymind.dtos;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.enums.Status;
import com.example.moneymind.enums.TipoDespesa;
import com.example.moneymind.enums.TipoPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespesaDTO implements Serializable {

    @NotBlank(message = "Por favor informe a descrição da despesa")
    private String descricao;

    private String estabelecimento;

    @NotNull(message = "Por favor informe a data de vencimento da despesa.")
    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    @NotNull
    private Status status = Status.EM_ABERTO;

    @NotBlank(message = "Por favor informe a data da despesa")
    private String data;

    @NotNull(message = "Por favor informe o valor da despesa")
    private BigDecimal valor;

    @NotNull(message = "Pot favor informe a forma de pagamento")
    private TipoPagamento tipoPagamento;

    @NotNull(message = "Por favor informe a categoria da despesa")
    private Long idCategoria;

    @NotNull(message = "Please enter the type of expense")
    private TipoDespesa tipoDespesa;

    public String getData() {
        try{
            YearMonth.parse(data);
            return data;
        }catch (DateTimeParseException e){
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Data inválida.");
        }
    }
}
