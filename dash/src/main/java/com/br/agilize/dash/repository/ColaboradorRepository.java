package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
    Optional<ColaboradorEntity> findByNome(String nome);

    Page<ColaboradorEntity> findByEmpresaId(Long empresaId, Pageable pageable);  
}
