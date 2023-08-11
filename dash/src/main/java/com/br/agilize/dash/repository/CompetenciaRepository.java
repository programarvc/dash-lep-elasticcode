package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.CompetenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenciaRepository extends JpaRepository<CompetenciaEntity, Long> {
}
