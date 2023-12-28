package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.CapacidadesRecomendadasEntity;

@Repository
public interface CapacidadesRecomendadasRepository extends JpaRepository<CapacidadesRecomendadasEntity, Long> {

}