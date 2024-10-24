package com.example.moneymind.mapper;

import com.example.moneymind.dtos.UpdateUserDTO;
import com.example.moneymind.dtos.UserDTO;
import com.example.moneymind.dtos.UserDataDTO;
import com.example.moneymind.entidades.Users;
import org.hibernate.sql.Update;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toObject(UserDTO userDTO);

    UserDTO toDTO(Users users);
    UserDataDTO toUserDataDTO(Users users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users updateFroDTO(UserDTO dto, @MappingTarget Users users);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users updateFroDTO(UpdateUserDTO dto, @MappingTarget Users users);
}
