package com.example.MoneyMind.mapper;

import com.example.MoneyMind.dtos.GastoNaoEssencialDTO;
import com.example.MoneyMind.entidades.GastoNaoEssencial;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GastoNaoEssencialMapper {

    GastoNaoEssencialMapper INSTANCE = Mappers.getMapper(GastoNaoEssencialMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GastoNaoEssencial toObject(GastoNaoEssencialDTO gastoNaoEssencialDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GastoNaoEssencialDTO toDTO(GastoNaoEssencial gastoNaoEssencial);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GastoNaoEssencial updateFromDTO(GastoNaoEssencialDTO dto, @MappingTarget GastoNaoEssencial gastoNaoEssencial);

}
