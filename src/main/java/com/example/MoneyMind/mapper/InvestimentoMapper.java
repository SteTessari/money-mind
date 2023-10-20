package com.example.MoneyMind.mapper;

import com.example.MoneyMind.dtos.InvestimentoDTO;
import com.example.MoneyMind.entidades.Investimento;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvestimentoMapper {

    InvestimentoMapper INSTANCE = Mappers.getMapper(InvestimentoMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Investimento toObject(InvestimentoDTO investimentoDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InvestimentoDTO toDTO(Investimento investimento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Investimento updateFromDTO(InvestimentoDTO dto, @MappingTarget Investimento investimento);

}
