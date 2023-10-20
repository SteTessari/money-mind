package com.example.MoneyMind.service;

import com.example.MoneyMind.dtos.GastoEssencialDTO;
import com.example.MoneyMind.enums.StatusConta;
import com.example.MoneyMind.mapper.GastoEssencialMapper;
import com.example.MoneyMind.repository.GastoEssencialRepository;
import com.example.MoneyMind.repository.LimitesRepository;
import com.example.MoneyMind.util.validacao.ValidacaoGastoEssencial;
import com.example.MoneyMind.entidades.GastoEssencial;
import com.example.MoneyMind.entidades.Limites;
import com.example.moneymind.exception.MoneyMindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class GastoEssencialService extends ValidacaoGastoEssencial {

    @Autowired
    private LimitesRepository limitesRepository;
    @Autowired
    private GastoEssencialRepository gastoEssencialRepository;

    private final GastoEssencialMapper gastoEssencialMapper = GastoEssencialMapper.INSTANCE;

    public void inserir (GastoEssencialDTO gastoEssencialDTO){
        if (gastoEssencialDTO.getDataPagamento() != null){
            gastoEssencialDTO.setStatusConta(StatusConta.PAGA);
        }

        if (gastoEssencialDTO.getDataPagamento() == null &&
        gastoEssencialDTO.getVencimento().isBefore(LocalDate.now())){
            gastoEssencialDTO.setStatusConta(StatusConta.ATRASADA);
        }

        GastoEssencial gastoEssencial = gastoEssencialMapper.toObject(gastoEssencialDTO);
        tratativasAntesDeInserir(gastoEssencial);
    }

    public void atualizar(Long idGastoEssencial, GastoEssencialDTO gastoEssencialDTO){
        GastoEssencial gastoEssencial = buscarPorId(idGastoEssencial);

        GastoEssencial gastoEssencialAtualizado = gastoEssencialMapper.updateFromDTO(
                gastoEssencialDTO, gastoEssencial);

        inserir(gastoEssencialAtualizado);
    }

    public GastoEssencialDTO buscarDTOPorId(Long idGastoEssencial){
        GastoEssencial gastoEssencial = gastoEssencialRepository.findById(idGastoEssencial)
                .orElseThrow(() -> new MoneyMindException(
                        HttpStatus.NO_CONTENT,
                        "Nenhum resultado encontrado"));

        return gastoEssencialMapper.toDTO(gastoEssencial);
    }

    public Page<GastoEssencialDTO> buscarTodos(GastoEssencialDTO gastoEssencialDTO, Pageable pageable){
        GastoEssencial gastoEssencial = gastoEssencialMapper.toObject(gastoEssencialDTO);

        Page<GastoEssencial> gastos = gastoEssencialRepository.findAll(Example.of(gastoEssencial), pageable);
        return gastos.map(gastoEssencialMapper::toDTO);
    }

    private void inserir (GastoEssencial gastoEssencial){
        gastoEssencialRepository.save(gastoEssencial);
    }

    private void tratativasAntesDeInserir(GastoEssencial gastoEssencial) {
        BigDecimal valor = gastoEssencial.getValor();

        List<GastoEssencial> gastos = gastoEssencialRepository
                .findByMes(gastoEssencial.getMes().toString());

        Limites limite = limitesRepository.findByMes(YearMonth.now().toString())
                .orElse(null);

        validarLimite(gastos, limite, valor);
        inserir(gastoEssencial);
    }

    private GastoEssencial buscarPorId(Long idGastoEssencial){
        return gastoEssencialRepository.findById(idGastoEssencial)
                .orElseThrow(() -> new MoneyMindException(
                        HttpStatus.NO_CONTENT,
                        "Nenhum resultado encontrado"));
    }
}
