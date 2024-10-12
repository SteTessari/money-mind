package com.example.moneymind.mapper;

import com.example.moneymind.dtos.UserDTO;
import com.example.moneymind.entidades.Users;
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
