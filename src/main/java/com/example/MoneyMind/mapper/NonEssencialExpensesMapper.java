package com.example.MoneyMind.mapper;

import com.example.MoneyMind.dtos.NonEssentialExpensesDTO;
import com.example.MoneyMind.entidades.NonEssentialExpenses;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NonEssencialExpensesMapper {

    NonEssencialExpensesMapper INSTANCE = Mappers.getMapper(NonEssencialExpensesMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NonEssentialExpenses toObject(NonEssentialExpensesDTO nonEssentialExpensesDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NonEssentialExpensesDTO toDTO(NonEssentialExpenses nonEssentialExpenses);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NonEssentialExpenses updateFromDTO(NonEssentialExpensesDTO dto, @MappingTarget NonEssentialExpenses nonEssentialExpenses);

}
