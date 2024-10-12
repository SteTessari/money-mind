package com.example.moneymind.mapper;

import com.example.moneymind.dtos.investimentsDTO;
import com.example.moneymind.entidades.Investiments;
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
