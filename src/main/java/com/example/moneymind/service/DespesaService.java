package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.DespesaDTO;
import com.example.moneymind.dtos.VerificarDespesaDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Despesa;
import com.example.moneymind.entidades.Limite;
import com.example.moneymind.enums.Status;
import com.example.moneymind.mapper.DespesaMapper;
import com.example.moneymind.repository.DespesaRepository;
import com.example.moneymind.repository.LimiteRepository;
import com.example.moneymind.util.validations.ValidateEssencialExpenses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService extends ValidateEssencialExpenses {

    private final LimiteRepository limiteRepository;
    private final DespesaRepository despesaRepository;

    private static final DespesaMapper despesaMapper = DespesaMapper.INSTANCE;

    public void criar(DespesaDTO despesaDTO, JwtTokenDTO jwtTokenDTO) {
        if (despesaDTO.getDataPagamento() != null) {
            despesaDTO.setStatus(Status.PAGA);
        }

        if (despesaDTO.getDataPagamento() == null &&
                despesaDTO.getDataVencimento().isBefore(LocalDate.now())) {
            despesaDTO.setStatus(Status.ATRASADA);
        }

        Despesa despesa = despesaMapper.toObject(despesaDTO);
        criar(despesa);
    }

    public void editar(Long idDespesa, DespesaDTO despesaDTO) {
        Despesa despesa = buscarPorId(idDespesa);

        Despesa despesaAtualizada = despesaMapper.updateFromDTO(
                despesaDTO, despesa);

        criar(despesaAtualizada);
    }

    public Page<DespesaDTO> buscarTodas(Long idUsuario, Pageable pageable) {

        Page<Despesa> despesas = despesaRepository.findByUsuario_Id(idUsuario, pageable);
        return despesas.map(despesaMapper::toDTO);
    }

    private void criar(Despesa despesa) {
        despesaRepository.save(despesa);
    }

    private Despesa buscarPorId(Long idGastoEssencial) {
        return despesaRepository.findById(idGastoEssencial)
                .orElseThrow(() -> new MoneyMindException(
                        HttpStatus.NO_CONTENT,
                        "Despesa não encontrada."));
    }

    public List<DespesaDTO> filtrar(String description) {
        return despesaRepository.findByDescricao(description)
                .stream()
                .map(despesaMapper::toDTO)
                .toList();
    }

    public String verificarLimiteAntesDeInserir(VerificarDespesaDTO despesaDTO, Long idUsuario) {
        BigDecimal value = despesaDTO.getValor();

        List<Despesa> despesas = buscarDespesasDoUsuarioPorCategoriaEMes(despesaDTO.getData(), despesaDTO.getIdCategoria(), idUsuario);

        Limite limite = limiteRepository.findByCategoria_IdAndDataAndUsuario_Id(
                        despesaDTO.getIdCategoria(), despesaDTO.getData(), idUsuario)
                .orElse(null);

        return validarLimite(despesas, limite, value);
    }

    public List<Despesa> buscarDespesasDoUsuarioPorCategoriaEMes(YearMonth data, Long idCategoria, Long idUsuario) {
        return despesaRepository
                .findByDataAndCategoria_IdAndUsuario_Id(data, idCategoria, idUsuario);
    }
}
