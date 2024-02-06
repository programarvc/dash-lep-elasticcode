package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.HabilidadeEntity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadeRepository extends JpaRepository<HabilidadeEntity, Long> {
    @Query(value = "SELECT new HabilidadeEntity(id, nome, backend) FROM HabilidadeEntity")
    Page<HabilidadeEntity> findAllHab(Pageable pageable);

    Optional<HabilidadeEntity> findByNome(String nome);
}