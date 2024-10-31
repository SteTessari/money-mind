package com.example.moneymind.mapper;

import com.example.moneymind.dtos.AtualizarUsuarioDTO;
import com.example.moneymind.dtos.UserDataDTO;
import com.example.moneymind.dtos.UsuarioDTO;
import com.example.moneymind.entidades.Usuario;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "despesas", ignore = true)
    @Mapping(target = "limites", ignore = true)
    @Mapping(target = "investimentos", ignore = true)
    @Mapping(target = "receitas", ignore = true)
    @Mapping(target = "id", ignore = true)
    Usuario toObject(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);

    UserDataDTO toUserDataDTO(Usuario usuario);

    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "despesas", ignore = true)
    @Mapping(target = "limites", ignore = true)
    @Mapping(target = "investimentos", ignore = true)
    @Mapping(target = "receitas", ignore = true)
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario updateFroDTO(UsuarioDTO dto, @MappingTarget Usuario usuario);

    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "despesas", ignore = true)
    @Mapping(target = "limites", ignore = true)
    @Mapping(target = "investimentos", ignore = true)
    @Mapping(target = "receitas", ignore = true)
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario updateFroDTO(AtualizarUsuarioDTO dto, @MappingTarget Usuario usuario);
}
