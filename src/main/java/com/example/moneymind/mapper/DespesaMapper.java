package com.example.moneymind.mapper;

import com.example.moneymind.dtos.DespesaDTO;
import com.example.moneymind.dtos.FiltroDespesa;
import com.example.moneymind.entidades.Despesa;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DespesaMapper {

    DespesaMapper INSTANCE = Mappers.getMapper(DespesaMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Despesa toObject(DespesaDTO despesaDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Despesa toObject(FiltroDespesa filtro);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DespesaDTO toDTO(Despesa despesa);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Despesa updateFromDTO(DespesaDTO dto, @MappingTarget Despesa despesa);

}
