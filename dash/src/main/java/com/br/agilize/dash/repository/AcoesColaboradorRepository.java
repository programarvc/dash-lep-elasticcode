package com.br.agilize.dash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.AcoesColaboradorEntity;
import com.br.agilize.dash.model.entity.ColaboradorEntity;

@Repository
public interface AcoesColaboradorRepository extends JpaRepository<AcoesColaboradorEntity, Long> {

    List<AcoesColaboradorEntity> findByColaborador(ColaboradorEntity colaborador);
}
