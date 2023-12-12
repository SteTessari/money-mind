package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitsRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByMonth(String month);


}
