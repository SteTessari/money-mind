package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDescription(String description);

    List<Expense> findByMonth(Month month);


}
