package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.MaturidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaturidadeRepository extends JpaRepository<MaturidadeEntity, Long> {
    
}