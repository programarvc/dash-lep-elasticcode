package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.MetaColaboradorEntity;

@Repository
public interface MetaColaboradorRepository extends JpaRepository<MetaColaboradorEntity, Long> {

     
   /*  @Query("SELECT mc FROM MetasColaboradorEntity mc WHERE mc.colaborador.id = :colaboradorId ORDER BY mc.data DESC")
    Stream<MetaColaboradorEntity> findByColaboradorIdOrderByDataDesc(@Param("colaboradorId") Long colaboradorId);
 */

   /* @Query("SELECT m FROM MetasOneAOneEntity m WHERE m.colaborador.id = :colaboradorId ORDER BY m.data DESC")
    Optional<MetasOneAOneEntity> findLatestByColaboradorId(@Param("colaboradorId") Long colaboradorId);*/
    
}