package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.NonEssentialExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NonEssencialExpensesRepository extends JpaRepository<NonEssentialExpenses, Long> {
    List<NonEssentialExpenses> findByMonth(String month);

}
