package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.config.security.TokenService;
import com.example.moneymind.dtos.AtualizarUsuarioDTO;
import com.example.moneymind.dtos.UserDataDTO;
import com.example.moneymind.dtos.UsuarioDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.dtos.authentication.LoginDTO;
import com.example.moneymind.entidades.Usuario;
import com.example.moneymind.mapper.UsuarioMapper;
import com.example.moneymind.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.moneymind.config.exception.MensagemDeExcecao.SENHA_INCORRETA;
import static com.example.moneymind.config.exception.MensagemDeExcecao.USUARIO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    private static final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public Long cadastrar(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioEncontrado = repository.findByEmail(usuarioDTO.getEmail());

        if (usuarioEncontrado.isPresent())
            throw new MoneyMindException(HttpStatus.CONFLICT, "O Email informado já existe.");

        if (usuarioDTO.getUsername() == null || usuarioDTO.getUsername().isBlank()) {
            String username = usuarioDTO.getEmail().substring(0, usuarioDTO.getEmail().indexOf("@"));
            username = username.replaceAll("_", ".");

            String novoUsername = gerarUsername(username);

            usuarioDTO.setUsername(novoUsername);
        } else {
            validarUsername(usuarioDTO.getUsername());

        }

        Usuario user = usuarioMapper.toObject(usuarioDTO);
        user.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        return gravar(user).getId();
    }

    private static void validarUsername(String username) {
        if (!username.matches("^[a-zA-Z0-9.]+$")) {
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "O username deve conter apenas letras, números e pontos.");
        }
    }

    public Usuario gravar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long idUsuario) {
        return repository.findById(idUsuario)
                .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, USUARIO_NAO_ENCONTRADO));
    }

    public UserDataDTO buscarDadosUsuarioPorId(Long idUsuario) {
        Usuario usuario = buscarPorId(idUsuario);

        return usuarioMapper.toUserDataDTO(usuario);
    }

    public String login(LoginDTO loginDTO) {
        Usuario user = repository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, USUARIO_NAO_ENCONTRADO));

        if (passwordEncoder.matches(loginDTO.senha(), user.getSenha())) {
            return this.tokenService.generateToken(user);
        } else {
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, SENHA_INCORRETA);
        }
    }

    public void editar(AtualizarUsuarioDTO atualizarUsuarioDTO, JwtTokenDTO jwtTokenDTO) {
        Optional<Usuario> usuarioOptional = repository.findById(jwtTokenDTO.getId());

        if (usuarioOptional.isEmpty()) {
            throw new MoneyMindException(HttpStatus.NOT_FOUND, USUARIO_NAO_ENCONTRADO);
        }

        Usuario usuario = usuarioOptional.get();

        boolean changedEmail = atualizarUsuarioDTO.getEmail() != null && !atualizarUsuarioDTO.getEmail().isBlank();
        if (changedEmail) {
            if (atualizarUsuarioDTO.getSenhaAtual() != null && !atualizarUsuarioDTO.getSenhaAtual().isBlank()) {
                if (!passwordEncoder.matches(atualizarUsuarioDTO.getSenhaAtual(), usuario.getSenha())) {
                    throw new MoneyMindException(HttpStatus.BAD_REQUEST, SENHA_INCORRETA);
                }
                repository.findByEmail(atualizarUsuarioDTO.getEmail())
                        .ifPresent(users -> {
                            throw new MoneyMindException(HttpStatus.CONFLICT, "O email informado já existe.");
                        });
            } else {
                throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Por favor informe a senha para atualizar o email.");
            }
        }

        boolean usernameAlterado = atualizarUsuarioDTO.getUsername() != null && !atualizarUsuarioDTO.getUsername().isBlank();
        if (usernameAlterado) {
            validarUsername(atualizarUsuarioDTO.getUsername());


            repository.findByUsername(atualizarUsuarioDTO.getUsername())
                    .ifPresent(users -> {
                        throw new MoneyMindException(HttpStatus.CONFLICT, "O username já existe.");
                    });
        }

        usuario = usuarioMapper.updateFroDTO(atualizarUsuarioDTO, usuario);
        repository.save(usuario);
    }

    public String gerarUsername(String username) {
        Optional<Usuario> usernameOptional = repository.findByUsername(username);

        if (usernameOptional.isPresent()) {
            int numero = 1;

            boolean contemNumero = username.matches(".*\\d.*");

            if (contemNumero) {
                String ultimosNumeros = username.replaceAll("\\D+", "");

                if (!ultimosNumeros.isEmpty()) {

                    numero = Integer.parseInt(ultimosNumeros) + 1;
                    username = username.substring(0, username.length() - ultimosNumeros.length());
                }
            }

            username = username + numero;

            return gerarUsername(username);
        }

        return username;
    }

}
