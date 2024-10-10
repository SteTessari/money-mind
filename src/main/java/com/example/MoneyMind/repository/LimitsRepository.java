package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.ExpenseLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitsRepository extends JpaRepository<ExpenseLimit, Long> {
    Optional<ExpenseLimit> findByIdUserAndIdCategoryAndMonth(Long idUser, Long idCategory, String month);
    Optional<ExpenseLimit> findByMonth(String month);


}
