package com.example.moneymind.repository;

import com.example.moneymind.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByUsuario_Id(Long idUsuario);

    boolean existsByDescricaoLikeAndUsuario_Id(String descricao, Long idUsuario);

    boolean existsCategoriaByIdAndUsuario_Id(Long id, Long idUsuario);

}
