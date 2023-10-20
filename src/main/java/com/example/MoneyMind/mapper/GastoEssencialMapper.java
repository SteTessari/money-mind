package com.example.MoneyMind.mapper;

import com.example.moneymind.dtos.GastoEssencialDTO;
import com.example.moneymind.dtos.GastoEssencialFiltro;
import com.example.moneymind.entidades.GastoEssencial;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GastoEssencialMapper {

    GastoEssencialMapper INSTANCE = Mappers.getMapper(GastoEssencialMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GastoEssencial toObject(GastoEssencialDTO gastoEssencialDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GastoEssencial toObject(GastoEssencialFiltro filtro);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GastoEssencialDTO toDTO(GastoEssencial gastoEssencial);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GastoEssencial updateFromDTO(GastoEssencialDTO dto, @MappingTarget GastoEssencial gastoEssencial);

}
