package com.example.moneymind.repository;

import com.example.moneymind.entidades.Limite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface LimiteRepository extends JpaRepository<Limite, Long> {
    Optional<Limite> findByCategoria_IdAndDataAndUsuario_Id(Long idCategoria, YearMonth data, Long idUsuario);

    Optional<Limite> findByCategoria_IdAndUsuario_Id(Long idCategoria, Long idUsuario);

    Optional<Limite> findByIdAndUsuario_Id(Long id, Long idUsuario);


}
