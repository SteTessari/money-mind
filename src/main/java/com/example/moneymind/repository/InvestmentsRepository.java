package com.example.moneymind.repository;

import com.example.moneymind.entidades.Investiments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentsRepository extends JpaRepository<Investiments, Long> {
}
