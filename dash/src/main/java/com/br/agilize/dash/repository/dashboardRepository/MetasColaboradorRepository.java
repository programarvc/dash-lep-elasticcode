package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.MetasColaboradorEntity;


@Repository
public interface MetasColaboradorRepository extends JpaRepository<MetasColaboradorEntity, Long> {

    @Query("SELECT mc FROM MetasColaboradorEntity mc WHERE mc.colaborador.id = :colaboradorId ORDER BY mc.data DESC")
    Stream<MetasColaboradorEntity> findByColaboradorIdOrderByDataDesc(@Param("colaboradorId") Long colaboradorId);

   /* @Query("SELECT m FROM MetasOneAOneEntity m WHERE m.colaborador.id = :colaboradorId ORDER BY m.data DESC")
    Optional<MetasOneAOneEntity> findLatestByColaboradorId(@Param("colaboradorId") Long colaboradorId);*/
}