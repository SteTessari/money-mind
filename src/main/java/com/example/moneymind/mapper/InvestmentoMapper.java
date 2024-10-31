package com.example.moneymind.mapper;

import com.example.moneymind.dtos.InvestimentoDTO;
import com.example.moneymind.entidades.Investimento;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvestmentoMapper {

    InvestmentoMapper INSTANCE = Mappers.getMapper(InvestmentoMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Investimento toObject(InvestimentoDTO investimentoDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InvestimentoDTO toDTO(Investimento investimento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Investimento updateFromDTO(InvestimentoDTO dto, @MappingTarget Investimento investimento);

}
