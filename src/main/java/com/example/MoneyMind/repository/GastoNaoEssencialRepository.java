package com.example.moneymind.repository;

import com.example.moneymind.entidades.GastoNaoEssencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface GastoNaoEssencialRepository extends JpaRepository<GastoNaoEssencial, Long> {
    List<GastoNaoEssencial> findByMes(YearMonth mes);

}
