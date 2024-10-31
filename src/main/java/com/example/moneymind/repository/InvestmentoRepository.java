package com.example.moneymind.repository;

import com.example.moneymind.entidades.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentoRepository extends JpaRepository<Investimento, Long> {
}
