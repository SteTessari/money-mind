package com.example.moneymind.service;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.entidades.Category;
import com.example.moneymind.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public void validateUserCategory(Long idCategory, Long idUser) {
        boolean existsCategoryByIdAndIdUser = categoryRepository.existsCategoryByIdAndIdUser(idCategory, idUser);

        if (!existsCategoryByIdAndIdUser)
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Category does not belong to the informed user.");
    }

    public void create(Category category) {
        userService.findById(category.getIdUser());

        boolean existsCategory = categoryRepository.existsByDescriptionLikeAndIdUser(category.getDescription(), category.getIdUser());

        if (!existsCategory) {
            categoryRepository.save(category);
        }

    }


}
