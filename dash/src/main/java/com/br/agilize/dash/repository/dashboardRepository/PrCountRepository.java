package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

import com.br.agilize.dash.model.entity.dashboardEntity.TimeColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.PrCountEntity;

@Repository
public interface PrCountRepository extends JpaRepository<PrCountEntity, Long> {
    
     @Query("SELECT p FROM PrCountEntity p WHERE p.timeColaborador.colaborador.id = :colaboradorId")
    PrCountEntity findByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    @Query("SELECT p FROM PrCountEntity p WHERE p.timeColaborador.id = :timeColaboradorId")
    PrCountEntity findByTimeColaboradorId(@Param("timeColaboradorId") Long timeColaboradorId);

   /* PrCountEntity findByTimeColaborador_Colaborador(ColaboradorEntity colaborador);

    @Query("SELECT p FROM PrCountEntity p WHERE p.timeColaborador.colaborador.id = :colaboradorId")
    PrCountEntity findByColaboradorId(@Param("colaboradorId") Long colaboradorId);*/
    
}