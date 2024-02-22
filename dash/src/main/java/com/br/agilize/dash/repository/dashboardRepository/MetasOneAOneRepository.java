package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.MetasOneAOneEntity;

@Repository
public interface MetasOneAOneRepository extends JpaRepository<MetasOneAOneEntity, Long> {
   /* @Query("SELECT m FROM MetasOneAOneEntity m WHERE m.colaborador.id = :colaboradorId ORDER BY m.data DESC")
    Optional<MetasOneAOneEntity> findLatestByColaboradorId(@Param("colaboradorId") Long colaboradorId);*/
}