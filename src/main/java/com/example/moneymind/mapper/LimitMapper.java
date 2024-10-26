package com.example.moneymind.mapper;

import com.example.moneymind.dtos.ExpenseLimitDTO;
import com.example.moneymind.entidades.ExpenseLimit;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LimitMapper {

    LimitMapper INSTANCE = Mappers.getMapper(LimitMapper.class);

    ExpenseLimit toObject(ExpenseLimitDTO expenseLimitDTO);

    ExpenseLimitDTO toDTO(ExpenseLimit limit);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ExpenseLimit updateFromDTO(ExpenseLimitDTO expenseLimitDTO, @MappingTarget ExpenseLimit limit);
}
