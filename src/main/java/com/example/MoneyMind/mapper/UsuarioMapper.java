package com.example.MoneyMind.mapper;

import com.example.MoneyMind.dtos.GastoEssencialDTO;
import com.example.MoneyMind.dtos.GastoEssencialFiltro;
import com.example.MoneyMind.dtos.UsuarioDTO;
import com.example.MoneyMind.entidades.GastoEssencial;
import com.example.MoneyMind.entidades.Usuario;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toObject(UsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario updateFromDTO(UsuarioDTO dto, @MappingTarget Usuario usuario);

}
