package com.example.MoneyMind.mapper;

import com.example.MoneyMind.dtos.UserDTO;
import com.example.MoneyMind.entidades.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toObject(UserDTO userDTO);

    UserDTO toDTO(Users users);

    Users updateFroDTO(UserDTO dto, @MappingTarget Users users);
}
