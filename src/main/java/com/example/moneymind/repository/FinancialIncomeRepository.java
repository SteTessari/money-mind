package com.example.moneymind.repository;

import com.example.moneymind.entidades.FinancialIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;

@Repository
public interface FinancialIncomeRepository extends JpaRepository<FinancialIncome, Long> {

    @Query("select sum(f.wage + f.extraIncome) from FinancialIncome f " +
            "where f.idUser = :idUser and f.month = :month and f.year = :year ")
    BigDecimal findWageByIdUserAndMonth(@Param("idUser") Long idUser,
                                           @Param("year") Integer year,
                                           @Param("month") Month month);
}
