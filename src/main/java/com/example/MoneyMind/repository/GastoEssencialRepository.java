package com.example.MoneyMind.repository;

import com.example.MoneyMind.entidades.GastoEssencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoEssencialRepository extends JpaRepository<GastoEssencial, Long> {
    List<GastoEssencial> findByIdUsuario(Long idUsuario);


}
