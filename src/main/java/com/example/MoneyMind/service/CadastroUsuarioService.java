package com.example.MoneyMind.service;

import br.com.sifat.core.exeptions.SifatServiceException;
import br.com.sifat.core.utils.StringUtils;
import com.example.MoneyMind.dtos.UsuarioDTO;
import com.example.MoneyMind.entidades.Usuario;
import com.example.MoneyMind.mapper.UsuarioMapper;
import com.example.MoneyMind.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public void cadastrar(UsuarioDTO usuarioDTO) {

        boolean cadastroJaExiste = usuarioRepository
                .existsByEmailAndAtivoTrue(usuarioDTO.getEmail());

        if (cadastroJaExiste) {
            throw new SifatServiceException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        String senha = usuarioDTO.getSenha();
        String confirmacaoSenha = usuarioDTO.getConfirmacaoSenha();

        if (!confirmacaoSenha.equals(senha)){
            throw new SifatServiceException(HttpStatus.BAD_REQUEST, "As duas senhas não são iguais");
        }

        senha = StringUtils.md5(usuarioDTO.getSenha());
        usuarioDTO.setSenha(senha);

        Usuario usuario = usuarioMapper.toObject(usuarioDTO);
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDate.now());
        usuario.setDataAlteracao(LocalDate.now());

        usuarioRepository.save(usuario);
    }
}
