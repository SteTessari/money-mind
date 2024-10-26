package com.example.moneymind.repository;

import com.example.moneymind.entidades.ExpenseLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitsRepository extends JpaRepository<ExpenseLimit, Long> {
    Optional<ExpenseLimit> findByIdAndIdUser(Long id, Long idUser);


}
