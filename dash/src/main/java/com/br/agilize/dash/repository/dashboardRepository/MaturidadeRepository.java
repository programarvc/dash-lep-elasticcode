package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.MaturidadeEntity;

@Repository
public interface MaturidadeRepository extends JpaRepository<MaturidadeEntity, Long> {

    /*@Query("SELECT new map(m.leadTime as leadTime, m.timeToRecovery as timeToRecovery, m.changeFailureRate as changeFailureRate, m.frequencyDeployment as frequencyDeployment) FROM MaturidadeEntity m WHERE m.esteira.id = :esteiraId")
    Map<String, Object> findByEsteiraId(@Param("esteiraId") Long esteiraId);*/

    @Query("SELECT new map(m.leadTime as leadTime, m.timeToRecovery as timeToRecovery, m.changeFailureRate as changeFailureRate, m.frequencyDeployment as frequencyDeployment, m.saude as saude, m.metricas4 as metricas4, m.capacidadeDora as capacidadeDora, m.mediaDeJornada as mediaDeJornada) FROM MaturidadeEntity m WHERE m.esteira.id = :esteiraId ORDER BY m.dataHora DESC")
    Stream<Map<String, Object>> findTopByEsteiraIdOrderByDataHoraDesc(@Param("esteiraId") Long esteiraId);

    Optional<MaturidadeEntity> findTopByEsteiraIdOrderByNumeroDesc(Long esteiraId);
 
}