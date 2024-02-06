package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.EsteiraDeDesenvolvimentoEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeEntity;

@Repository
public interface TimeColaboradorRepository extends JpaRepository<TimeColaboradorEntity, Long> {

    
/*@Query("SELECT t FROM TimeColaboradorEntity t WHERE t.esteira.id = :esteiraId")
List<TimeColaboradorEntity> findTimeAndColaboradorByEsteiraId(@Param("esteiraId") Long esteiraId);*/

/*@Query("SELECT tc.time FROM TimeColaboradorEntity tc WHERE tc.time.esteira.id = :esteiraId AND tc.colaborador.id = :colaboradorId")
    List<TimeEntity> findTimesByEsteiraIdAndColaboradorId(@Param("esteiraId") Long esteiraId, @Param("colaboradorId") Long colaboradorId);

@Query("SELECT tc.colaborador FROM TimeColaboradorEntity tc WHERE tc.time.esteira.id = :esteiraId AND tc.time.id = :timeId")
List<ColaboradorEntity> findColaboradoresByEsteiraIdAndTimeId(@Param("esteiraId") Long esteiraId, @Param("timeId") Long timeId);*/

@Query("SELECT  tc.colaborador FROM TimeColaboradorEntity tc WHERE tc.time.esteira.id = :esteiraId")
List<ColaboradorEntity> findColaboradoresByEsteiraId(@Param("esteiraId") Long esteiraId);

@Query("SELECT  tc.time FROM TimeColaboradorEntity tc WHERE tc.time.esteira.id = :esteiraId")
List<TimeEntity> findTimesByEsteiraId(@Param("esteiraId") Long esteiraId);

@Query("SELECT  tc.colaborador FROM TimeColaboradorEntity tc WHERE tc.time.id = :timeId")
List<ColaboradorEntity> findColaboradoresByTimeId(@Param("timeId") Long timeId);

@Query("SELECT tc FROM TimeColaboradorEntity tc WHERE tc.time.esteira.id = :esteiraId")
List<TimeColaboradorEntity> findTimeAndColaboradorByEsteiraId(@Param("esteiraId") Long esteiraId);

@Query("SELECT tc.time FROM TimeColaboradorEntity tc WHERE tc.colaborador.id = :colaboradorId")
List<TimeEntity> findTimesAndEsteiraByColaboradorId(@Param("colaboradorId") Long colaboradorId);
}