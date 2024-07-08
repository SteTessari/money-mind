package com.example.MoneyMind.service;

import com.example.MoneyMind.dtos.UserDTO;
import com.example.MoneyMind.entidades.Users;
import com.example.MoneyMind.exception.MoneyMindException;
import com.example.MoneyMind.mapper.UserMapper;
import com.example.MoneyMind.repository.UserRepository;
import com.example.MoneyMind.util.validations.ValidateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ValidateUserService {
    @Autowired
    private UserRepository repository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public Long create(UserDTO userDTO) {
        Optional<Users> usuarioEncontrado = repository.findByUsername(userDTO.getUsername());

        if (usuarioEncontrado.isPresent())
            throw new MoneyMindException(HttpStatus.CONFLICT, "The username already exists.");

        if (!isSenhaValida(userDTO))
            throw new MoneyMindException(HttpStatus.BAD_REQUEST,
                    "The password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit and 1 special character.");

//        userDTO.setPassword(passwordEncoder().encode(userDTO.getPassword()));
        return gravar(userDTO).getId();
    }

    public Users gravar(UserDTO userDTO) {
        Users users = userMapper.toObject(userDTO);
        return repository.save(users);
    }
}
