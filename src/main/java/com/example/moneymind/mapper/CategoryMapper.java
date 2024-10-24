package com.example.moneymind.mapper;

import com.example.moneymind.dtos.CategoryDTO;
import com.example.moneymind.entidades.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toObject(CategoryDTO categoryDTO);
    CategoryDTO toDTO(Category category);
    Category updateFromDTO(CategoryDTO categoryDTO, @MappingTarget Category category);
}
