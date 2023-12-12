package com.example.MoneyMind.mapper;

import com.example.MoneyMind.dtos.investimentsDTO;
import com.example.MoneyMind.entidades.Investiments;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvestmentsMapper {

    InvestmentsMapper INSTANCE = Mappers.getMapper(InvestmentsMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Investiments toObject(investimentsDTO investimentsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    investimentsDTO toDTO(Investiments investiments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Investiments updateFromDTO(investimentsDTO dto, @MappingTarget Investiments investiments);

}
