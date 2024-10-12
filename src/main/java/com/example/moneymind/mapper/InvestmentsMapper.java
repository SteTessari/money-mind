package com.example.moneymind.mapper;

import com.example.moneymind.dtos.InvestimentsDTO;
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
    Investiments toObject(InvestimentsDTO investimentsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InvestimentsDTO toDTO(Investiments investiments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Investiments updateFromDTO(InvestimentsDTO dto, @MappingTarget Investiments investiments);

}
