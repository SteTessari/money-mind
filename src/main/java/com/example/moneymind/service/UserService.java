package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.config.security.TokenService;
import com.example.moneymind.dtos.UpdateUserDTO;
import com.example.moneymind.dtos.UserDTO;
import com.example.moneymind.dtos.UserDataDTO;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
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
            throw new MoneyMindException(HttpStatus.CONFLICT, "The email already exists.");

        if (!isSenhaValida(userDTO))
            throw new MoneyMindException(HttpStatus.BAD_REQUEST,
                    "The password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit and 1 special character.");

        if (userDTO.getUsername() == null || userDTO.getUsername().isBlank()) {
            String username = userDTO.getEmail().substring(0, userDTO.getEmail().indexOf("@"));
            username = username.replaceAll("_", ".");

            String newUsername = validateUsername(username);

            userDTO.setUsername(newUsername);
        } else {
            if (!userDTO.getUsername().matches(".*[a-zA-Z].*")) {
                throw new MoneyMindException(HttpStatus.BAD_REQUEST, "The username must contain letters.");
            }
        }

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

    public UserDataDTO findUser(Long idUser) {
        Users user = findById(idUser);

        return userMapper.toUserDataDTO(user);
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

    public void update(UpdateUserDTO updateUserDTO, JwtTokenDTO jwtTokenDTO) {
        Optional<Users> userOptional = repository.findById(jwtTokenDTO.getId());

        if (userOptional.isEmpty()) {
            throw new MoneyMindException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
        }

        Users user = userOptional.get();

        boolean changedEmail = updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isBlank();
        if (changedEmail) {
            if (updateUserDTO.getCurrentPassword() != null && !updateUserDTO.getCurrentPassword().isBlank()) {
                if (!passwordEncoder.matches(updateUserDTO.getCurrentPassword(), user.getPassword())) {
                    throw new MoneyMindException(HttpStatus.BAD_REQUEST, INCORRECT_PASSWORD);
                }
                repository.findByEmail(updateUserDTO.getEmail())
                        .ifPresent(users -> {
                            throw new MoneyMindException(HttpStatus.CONFLICT, "The email already exists.");
                        });
            } else {
                throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Please enter your password to update your email.");
            }
        }

        boolean changedUsername = updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isBlank();
        if (changedUsername) {
            if (!updateUserDTO.getUsername().matches(".*[a-zA-Z].*")) {
                throw new MoneyMindException(HttpStatus.BAD_REQUEST, "The username must contain letters.");
            }

            repository.findByUsername(updateUserDTO.getUsername())
                    .ifPresent(users -> {
                        throw new MoneyMindException(HttpStatus.CONFLICT, "The username already exists.");
                    });
        }

        user = userMapper.updateFroDTO(updateUserDTO, user);
        repository.save(user);
    }

    public String validateUsername(String username) {
        Optional<Users> usernameOptional = repository.findByUsername(username);

        if (usernameOptional.isPresent()) {
            int number = 1;

            boolean containsNumber = username.matches(".*\\d.*");

            if (containsNumber) {
                String lastNumber = username.replaceAll("\\D+", "");

                if (!lastNumber.isEmpty()) {

                    number = Integer.parseInt(lastNumber) + 1;
                    username = username.substring(0, username.length() - lastNumber.length());
                }
            }

            username = username + number;

            return validateUsername(username);
        }

        return username;
    }

}
