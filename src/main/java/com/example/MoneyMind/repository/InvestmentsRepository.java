package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.Investiments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentsRepository extends JpaRepository<Investiments, Long> {
}
