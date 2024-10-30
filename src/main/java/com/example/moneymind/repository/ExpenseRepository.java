package com.example.moneymind.repository;

import com.example.moneymind.entidades.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDescription(String description);

    Page<Expense> findByIdUser(Long idUser, Pageable pageable);

    List<Expense> findByIdUserAndMonthAndIdCategory(Long idUser, String month, Long idCategory);


    @Query("select sum(e.value) from Expense e " +
            "where e.idUser = :idUser and e.month = :month and e.year = :year " +
            "and e.formOfPayment = 'DEBIT' ")
    BigDecimal findTotalExpenseByMonthAndYearAndIdUser(@Param("month") Month month,
                                                       @Param("year") Integer year,
                                                       @Param("idUser") Long idUser);

}
