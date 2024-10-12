package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.config.security.TokenService;
import com.example.moneymind.dtos.UserDTO;
import com.example.moneymind.dtos.authentication.LoginRequestDTO;
import com.example.moneymind.entidades.Users;
import com.example.moneymind.mapper.UserMapper;
import com.example.moneymind.repository.UserRepository;
import com.example.moneymind.util.validations.ValidateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.moneymind.config.exception.ExceptionMessages.INCORRECT_PASSWORD;
import static com.example.moneymind.config.exception.ExceptionMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService extends ValidateUserService {
    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    private static final UserMapper userMapper = UserMapper.INSTANCE;

    public Long create(UserDTO userDTO) {
        Optional<Users> userFound = repository.findByEmail(userDTO.getEmail());

        if (userFound.isPresent())
            throw new MoneyMindException(HttpStatus.CONFLICT, "The username already exists.");

        if (!isSenhaValida(userDTO))
            throw new MoneyMindException(HttpStatus.BAD_REQUEST,
                    "The password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit and 1 special character.");

        Users user = userMapper.toObject(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return gravar(user).getId();
    }

    public Users gravar(Users users) {
        return repository.save(users);
    }

    public Users findById(Long idUser) {
        return repository.findById(idUser)
                .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));
    }

    public String login(LoginRequestDTO body) {
        Users user = repository.findByEmail(body.email())
                .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            return this.tokenService.generateToken(user);
        } else {
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, INCORRECT_PASSWORD);
        }
    }

}
