package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.CategoriaDTO;
import com.example.moneymind.entidades.Categoria;
import com.example.moneymind.entidades.Usuario;
import com.example.moneymind.mapper.CategoriaMapper;
import com.example.moneymind.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioService usuarioService;
    private static final CategoriaMapper CATEGORIA_MAPPER = CategoriaMapper.INSTANCE;

    public void validarCategoriaDoUsuario(Long idCategoria, Long idUsuario) {
        boolean existeCategoriaParaUsuarioInformado = categoriaRepository.existsCategoriaByIdAndUsuario_Id(idCategoria, idUsuario);

        if (!existeCategoriaParaUsuarioInformado)
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Categoria não encontrada.");
    }

    public void criar(CategoriaDTO categoriaDTO, Long idUsuario) {
        Categoria categoria = CATEGORIA_MAPPER.toObject(categoriaDTO);
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        categoria.setUsuario(usuario);

        boolean categoriaExiste = categoriaRepository.existsByDescricaoLikeAndUsuario_Id(categoria.getDescricao(), categoria.getUsuario().getId());

        if (!categoriaExiste) {
            categoriaRepository.save(categoria);
        }

    }


    public List<Categoria> buscarTodas(Long idUsuario) {
        return categoriaRepository.findByUsuario_Id(idUsuario);
    }

    public void editar(Long idCategoria, CategoriaDTO categoriaDTO, Long idUsuario) {
        usuarioService.buscarPorId(idUsuario);

        Categoria categoria = buscarPorId(idCategoria);

        categoria = CATEGORIA_MAPPER.updateFromDTO(categoriaDTO, categoria);

        categoriaRepository.save(categoria);
    }

    public Categoria buscarPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
    }
}
