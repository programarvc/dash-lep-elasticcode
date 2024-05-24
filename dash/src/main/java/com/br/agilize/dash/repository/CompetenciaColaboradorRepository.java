package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.CompetenciaColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetenciaColaboradorRepository extends JpaRepository<CompetenciaColaboradorEntity, Long> {

    List<CompetenciaColaboradorEntity> findByColaborador(ColaboradorEntity colaborador);

    CompetenciaColaboradorEntity findByColaboradorIdAndCompetenciaId(Long colaboradorId, Long competenciaId);
}