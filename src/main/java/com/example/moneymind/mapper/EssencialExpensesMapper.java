package com.example.moneymind.mapper;

import com.example.moneymind.dtos.ExpenseDTO;
import com.example.moneymind.dtos.ExpenseFilter;
import com.example.moneymind.entidades.Expense;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EssencialExpensesMapper {

    EssencialExpensesMapper INSTANCE = Mappers.getMapper(EssencialExpensesMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Expense toObject(ExpenseDTO expenseDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Expense toObject(ExpenseFilter filtro);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ExpenseDTO toDTO(Expense expense);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Expense updateFromDTO(ExpenseDTO dto, @MappingTarget Expense expense);

}
