package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.EssentialExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssencialExpensesRepository extends JpaRepository<EssentialExpenses, Long> {
    List<EssentialExpenses> findByDescription(String description);
    List<EssentialExpenses> findByMonth(String month);


}
