package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.TimeEsteiraEntity;

@Repository
public interface TimeEsteiraRepository extends JpaRepository<TimeEsteiraEntity, Long> {

    List<TimeEsteiraEntity> findByEsteiraId(Long esteiraId);

    @Query("SELECT new map(t.time.nome as nomeTime, t.colaborador.nome as nomeColaborador) FROM TimeEsteiraEntity t WHERE t.esteira.id = :esteiraId")
    List<Map<String, String>> findTimeAndColaboradorNamesByEsteiraId(@Param("esteiraId") Long esteiraId);

    
   


}