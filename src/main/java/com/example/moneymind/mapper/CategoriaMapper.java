package com.example.moneymind.mapper;

import com.example.moneymind.dtos.CategoriaDTO;
import com.example.moneymind.entidades.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    Categoria toObject(CategoriaDTO categoriaDTO);

    CategoriaDTO toDTO(Categoria categoria);

    Categoria updateFromDTO(CategoriaDTO categoriaDTO, @MappingTarget Categoria categoria);
}
