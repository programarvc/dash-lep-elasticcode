package com.br.agilize.dash.repository.dashboardRepository;

import java.time.LocalDate;
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
   //Query para buscar as 3 competencias com as ultimas  notas de uma competencia de um colaborador em uma data
   @Query(value = "SELECT c.nome AS competencia, mc.nota AS nota " +
      "FROM metacolaboradorentity mc " +
      "JOIN competenciaentity c ON mc.competencia_id = c.id " +
      "WHERE mc.data = CURRENT_DATE AND mc.colaborador_id = :colaboradorId " +
      "ORDER BY mc.nota DESC ", nativeQuery = true)
   List<Map<String, Object>> findTop3CompetenciasByColaborador(@Param("colaboradorId") Long colaboradorId);

   //As consultas abaixo são usadas no metodo so service para capitar a lista de competencias e notas em uma data especifica
   //Query para buscar as datas com pelo menos 3 competencias de um colaborador
   @Query(value = "SELECT mc.data " +
      "FROM metacolaboradorentity mc " +
      "WHERE mc.colaborador_id = :colaboradorId " +
      "GROUP BY mc.data " +
      "HAVING COUNT(DISTINCT mc.competencia_id) >= 1",
       nativeQuery = true)
   List<Object[]> findDatasWithAtLeast3Competencias(@Param("colaboradorId") Long colaboradorId);

   //Query para buscar as competencias com as notas de um colaborador em uma data
   @Query(value = "SELECT c.nome AS competencia, mc.nota AS nota, mc.data AS data " +
      "FROM metacolaboradorentity mc " +
      "JOIN competenciaentity c ON mc.competencia_id = c.id " +
      "WHERE mc.data = :data AND mc.colaborador_id = :colaboradorId " +
      "ORDER BY mc.nota DESC ",
       nativeQuery = true)
   List<Map<String, Object>> findTop3CompetenciasByColaboradorAndData(@Param("colaboradorId") Long colaboradorId, @Param("data") LocalDate data);
   
   /*  @Query("SELECT mc FROM MetasColaboradorEntity mc WHERE mc.colaborador.id = :colaboradorId ORDER BY mc.data DESC")
    Stream<MetaColaboradorEntity> findByColaboradorIdOrderByDataDesc(@Param("colaboradorId") Long colaboradorId);
 */

   /* @Query("SELECT m FROM MetasOneAOneEntity m WHERE m.colaborador.id = :colaboradorId ORDER BY m.data DESC")
    Optional<MetasOneAOneEntity> findLatestByColaboradorId(@Param("colaboradorId") Long colaboradorId);*/
    
}