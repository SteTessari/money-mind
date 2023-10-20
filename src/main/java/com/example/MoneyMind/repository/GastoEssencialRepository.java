package com.example.moneymind.repository;

import com.example.moneymind.entidades.GastoEssencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoEssencialRepository extends JpaRepository<GastoEssencial, Long> {
    List<GastoEssencial> findByIdUsuario(Long idUsuario);


}
