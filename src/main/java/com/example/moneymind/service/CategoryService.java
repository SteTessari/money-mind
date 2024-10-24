package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.CategoryDTO;
import com.example.moneymind.entidades.Category;
import com.example.moneymind.mapper.CategoryMapper;
import com.example.moneymind.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    public void validateUserCategory(Long idCategory, Long idUser) {
        boolean existsCategoryByIdAndIdUser = categoryRepository.existsCategoryByIdAndIdUser(idCategory, idUser);

        if (!existsCategoryByIdAndIdUser)
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Category does not belong to the informed user.");
    }

    public void create(CategoryDTO categoryDTO, Long idUser) {
        Category category = categoryMapper.toObject(categoryDTO);
        category.setIdUser(idUser);

        boolean existsCategory = categoryRepository.existsByDescriptionLikeAndIdUser(category.getDescription(), category.getIdUser());

        if (!existsCategory) {
            categoryRepository.save(category);
        }

    }


    public List<Category> findAll(Long idUser) {
        return categoryRepository.findByIdUser(idUser);
    }
}
