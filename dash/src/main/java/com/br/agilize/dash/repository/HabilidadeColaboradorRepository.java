package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.HabilidadeColaboradorEntity;

import com.br.agilize.dash.model.entity.HabilidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadeColaboradorRepository extends JpaRepository<HabilidadeColaboradorEntity, Long> {

    List<HabilidadeColaboradorEntity> findByColaborador(ColaboradorEntity colaborador);

    List<HabilidadeColaboradorEntity> findByHabilidade(HabilidadeEntity habilidade);
}
