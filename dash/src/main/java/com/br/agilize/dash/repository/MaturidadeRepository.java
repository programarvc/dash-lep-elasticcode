package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.MaturidadeEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaturidadeRepository extends JpaRepository<MaturidadeEntity, Long> {

    List<MaturidadeEntity> findByEsteiraId(Long esteiraId);

    Optional<MaturidadeEntity> findTopByEsteiraIdOrderByNumeroDesc(Long esteiraId);
    
}