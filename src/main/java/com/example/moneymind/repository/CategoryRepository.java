package com.example.moneymind.repository;

import com.example.moneymind.entidades.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIdUser(Long idUser);
    boolean existsByDescriptionLikeAndIdUser(String description, Long idUser);

    boolean existsCategoryByIdAndIdUser(Long id, Long idUser);

}
