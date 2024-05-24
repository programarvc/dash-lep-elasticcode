package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.HabilidadeColaboradorEntity;

import com.br.agilize.dash.model.entity.HabilidadeEntity;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface HabilidadeColaboradorRepository extends JpaRepository<HabilidadeColaboradorEntity, Long> {

    List<HabilidadeColaboradorEntity> findByHabilidade(HabilidadeEntity habilidade);
    List<HabilidadeColaboradorEntity> findByColaborador(ColaboradorEntity colaborador);

    List<HabilidadeColaboradorEntity> findByHabilidadeId(Long id);
    HabilidadeColaboradorEntity findByColaboradorIdAndHabilidadeId(Long colaboradorId, Long habilidadeId);

    @Query(value = "SELECT hc.habilidade FROM HabilidadeColaboradorEntity hc WHERE hc.colaborador.id = :colaboradorId ORDER BY hc.habilidade.id ASC")
    List<HabilidadeEntity> findTop2HabilidadesByColaboradorId(@Param("colaboradorId") Long colaboradorId, Pageable pageable);
}