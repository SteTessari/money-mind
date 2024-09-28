package com.example.MoneyMind.service;

import com.example.MoneyMind.config.exception.ExceptionMessages;
import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.config.security.TokenService;
import com.example.MoneyMind.dtos.UserDTO;
import com.example.MoneyMind.dtos.authentication.AuthenticatedUserDTO;
import com.example.MoneyMind.dtos.authentication.LoginRequestDTO;
import com.example.MoneyMind.entidades.Users;
import com.example.MoneyMind.mapper.UserMapper;
import com.example.MoneyMind.repository.UserRepository;
import com.example.MoneyMind.util.validations.ValidateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends ValidateUserService {
    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public Long create(UserDTO userDTO) {
        Optional<Users> userFound = repository.findByEmail(userDTO.getEmail());

        if (userFound.isPresent())
            throw new MoneyMindException(HttpStatus.CONFLICT, "The username already exists.");

        if (!isSenhaValida(userDTO))
            throw new MoneyMindException(HttpStatus.BAD_REQUEST,
                    "The password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit and 1 special character.");

        Users user = userMapper.toObject(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return gravar(userDTO).getId();
    }

    public Users gravar(UserDTO userDTO) {
        Users users = userMapper.toObject(userDTO);
        return repository.save(users);
    }

    public Users findById(Long idUser) {
        return repository.findById(idUser)
                .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, ExceptionMessages.USER_NOT_FOUND));
    }

    public AuthenticatedUserDTO login(LoginRequestDTO body) {
        Users user = repository.findByEmail(body.email())
                .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, ExceptionMessages.USER_NOT_FOUND));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            return new AuthenticatedUserDTO(user.getUsername(), token);
        } else {
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, ExceptionMessages.INCORRECT_PASSWORD);
        }
    }
}
