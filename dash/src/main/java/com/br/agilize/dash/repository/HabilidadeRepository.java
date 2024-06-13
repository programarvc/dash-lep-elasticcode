package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.HabilidadeEntity;

import java.util.List;
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

    @Query(value = "SELECT * FROM habilidadeentity WHERE nome IN ('Full Stack', 'Front End', 'Back End')", nativeQuery = true)
    List<HabilidadeEntity> getDevStacks();
    
    @Query(value = "SELECT * FROM habilidadeentity WHERE nome NOT IN ('Full Stack', 'Front End', 'Back End')", nativeQuery = true)
    List<HabilidadeEntity> getDevTechnologies();
}