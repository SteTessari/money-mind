package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.LimiteDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Categoria;
import com.example.moneymind.entidades.Despesa;
import com.example.moneymind.entidades.Limite;
import com.example.moneymind.entidades.Usuario;
import com.example.moneymind.mapper.LimiteMapper;
import com.example.moneymind.repository.LimiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimiteService {
    private final LimiteRepository limiteRepository;
    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;
    private final DespesaService despesaService;
    private final LimiteMapper limiteMapper = LimiteMapper.INSTANCE;

    public void criar(LimiteDTO limiteDTO, Long idUsuario) {

        Limite limite = limiteMapper.toObject(limiteDTO);

        Categoria categoria = categoriaService.buscarPorId(limiteDTO.getIdCategoria());
        limite.setCategoria(categoria);

        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        limite.setUsuario(usuario);

        Optional<Limite> limiteOptional = limiteRepository.findByCategoria_IdAndUsuario_Id(limiteDTO.getIdCategoria(), idUsuario);
        if (limiteOptional.isPresent()) {
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Limite já cadastrado para a categoria informada.");
        }

        categoriaService.validarCategoriaDoUsuario(limiteDTO.getIdCategoria(), idUsuario);

        limiteRepository.save(limite);
    }

    public Limite buscarPorId(Long idUsuario, Long idLimite) {
        Optional<Limite> limiteOptional = limiteRepository.findByIdAndUsuario_Id(idLimite, idUsuario);

        if (limiteOptional.isPresent()) {
            return limiteOptional.get();
        } else {
            throw new MoneyMindException(HttpStatus.NOT_FOUND, "Limite não encontrado.");
        }
    }

    public void editar(Long idLimite, LimiteDTO limiteDTO, JwtTokenDTO jwtTokenDTO) {

        Limite limite = buscarPorId(jwtTokenDTO.getId(), idLimite);

        limite = limiteMapper.updateFromDTO(limiteDTO, limite);

        limiteRepository.save(limite);
    }

    public void deletar(Long idLimite, Long idUsuario) {
        buscarPorId(idUsuario, idLimite);

        limiteRepository.deleteById(idLimite);
    }

    public String verificarLimiteAntesDeAtualizar(Long idLimite, LimiteDTO limiteDTO, Long idUsuario) {
        buscarPorId(idUsuario, idLimite);

        limiteRepository.findByIdAndCategoria_Id(idLimite, limiteDTO.getIdCategoria())
                .orElseThrow(() -> new MoneyMindException(HttpStatus.BAD_REQUEST, "O limite informado não pertence à categoria informada."));

        List<Despesa> despesas = despesaService.buscarDespesasDoUsuarioPorCategoriaEMes(
                limiteDTO.getData(), limiteDTO.getIdCategoria(), idUsuario);

        BigDecimal totalDespesas = despesas.stream().map(Despesa::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (limiteDTO.getLimite().compareTo(totalDespesas) < 0) {
            return "O novo limite definido é inferior ao total de despesas. Isso será refletido no relatório. Deseja continuar mesmo assim?";
        } else return null;
    }
}
