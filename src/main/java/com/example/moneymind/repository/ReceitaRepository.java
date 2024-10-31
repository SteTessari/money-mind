package com.example.moneymind.repository;

import com.example.moneymind.entidades.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.YearMonth;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query("select sum(f.salario + f.rendaExtra) from Receita f " +
            "where f.usuario.id = :idUsuario and f.data = :data ")
    BigDecimal findSalarioByDataAndUsuario_Id(@Param("data") YearMonth data, @Param("idUsuario") Long idUsuario);
}
