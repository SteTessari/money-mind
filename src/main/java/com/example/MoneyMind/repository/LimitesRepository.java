package com.example.MoneyMind.repository;

import com.example.moneymind.entidades.Limites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface LimitesRepository extends JpaRepository<Limites, Long> {
    Optional<Limites> findByMes(YearMonth mes);


}
