package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.FinancialIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialIncomeRepository extends JpaRepository<FinancialIncome, Long> {
}
