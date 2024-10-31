package com.example.moneymind.mapper;

import com.example.moneymind.dtos.LimiteDTO;
import com.example.moneymind.entidades.Limite;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LimiteMapper {

    LimiteMapper INSTANCE = Mappers.getMapper(LimiteMapper.class);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "id", ignore = true)
    Limite toObject(LimiteDTO limiteDTO);

    @Mapping(target = "idCategoria", ignore = true)
    LimiteDTO toDTO(Limite limit);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Limite updateFromDTO(LimiteDTO limiteDTO, @MappingTarget Limite limit);
}
