package com.example.moneymind.repository;

import com.example.moneymind.entidades.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByDescricao(String descricao);

    Page<Despesa> findByUsuario_Id(Long idUsuario, Pageable pageable);

    List<Despesa> findByDataAndCategoria_IdAndUsuario_Id(YearMonth data, Long idCategoria, Long idUsuario);


    @Query("select sum(e.valor) from Despesa e " +
            "where e.usuario.id = :idUsuario and e.data = :data " +
            "and e.tipoPagamento = 'DEBIT' ")
    BigDecimal findTotalExpenseByMonthAndYearAndidUsuario(@Param("data") YearMonth data,
                                                          @Param("idUsuario") Long idUsuario);

}
