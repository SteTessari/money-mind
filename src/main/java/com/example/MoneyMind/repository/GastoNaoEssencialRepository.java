package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.GastoNaoEssencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface GastoNaoEssencialRepository extends JpaRepository<GastoNaoEssencial, Long> {
    List<GastoNaoEssencial> findByMes(String mes);

}
