package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.CapacidadesRecomendadasEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacidadesRecomendadasRepository extends JpaRepository<CapacidadesRecomendadasEntity, Long> {

}