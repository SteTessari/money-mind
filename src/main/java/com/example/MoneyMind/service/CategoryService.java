package com.example.MoneyMind.service;

import com.example.MoneyMind.entidades.Category;
import com.example.MoneyMind.config.exception.MoneyMindException;
import com.example.MoneyMind.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    public void validateUserCategory(Long idCategory, Long idUser) {
        boolean existsCategoryByIdAndIdUser = categoryRepository.existsCategoryByIdAndIdUser(idCategory, idUser);

        if (!existsCategoryByIdAndIdUser)
            throw new MoneyMindException(HttpStatus.BAD_REQUEST, "Category does not belong to the informed user.");
    }

    public void create(Category category) {
        userService.findById(category.getIdUser());

        categoryRepository.save(category);
    }
}