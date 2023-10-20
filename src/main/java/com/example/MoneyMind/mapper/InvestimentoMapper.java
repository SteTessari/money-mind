package com.example.moneymind.mapper;

import com.example.moneymind.dtos.GastoEssencialDTO;
import com.example.moneymind.dtos.InvestimentoDTO;
import com.example.moneymind.entidades.GastoEssencial;
import com.example.moneymind.entidades.GastoNaoEssencial;
import com.example.moneymind.entidades.Investimento;
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
    GastoEssencial updateFromDTO(GastoEssencialDTO dto, @MappingTarget GastoEssencial gastoEssencial);

}
