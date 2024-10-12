package com.example.moneymind.repository;

import com.example.moneymind.entidades.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsCategoryByIdAndIdUser(Long id, Long idUser);

}
