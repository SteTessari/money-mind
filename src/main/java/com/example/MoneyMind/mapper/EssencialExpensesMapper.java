package com.example.MoneyMind.mapper;

import com.example.MoneyMind.dtos.EssentialExpensesDTO;
import com.example.MoneyMind.dtos.EssentialExpensesFilter;
import com.example.MoneyMind.entidades.EssentialExpenses;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EssencialExpensesMapper {

    EssencialExpensesMapper INSTANCE = Mappers.getMapper(EssencialExpensesMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EssentialExpenses toObject(EssentialExpensesDTO essentialExpensesDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EssentialExpenses toObject(EssentialExpensesFilter filtro);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EssentialExpensesDTO toDTO(EssentialExpenses essentialExpenses);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EssentialExpenses updateFromDTO(EssentialExpensesDTO dto, @MappingTarget EssentialExpenses essentialExpenses);

}
