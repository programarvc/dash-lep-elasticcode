package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.br.agilize.dash.model.entity.dashboardEntity.TimeColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.PrCountEntity;

@Repository
public interface PrCountRepository extends JpaRepository<PrCountEntity, Long> {
    
    /*  @Query("SELECT p FROM PrCountEntity p WHERE p.timeColaborador.colaborador.id = :colaboradorId")
    PrCountEntity findByColaboradorId(@Param("colaboradorId") Long colaboradorId); */

    @Query(value = "SELECT pr.* " +
                   "FROM pr_count pr " +
                   "INNER JOIN timecolaboradorentity tc ON pr.time_colaborador_id = tc.id " +
                   "INNER JOIN colaboradorentity c ON tc.colaborador_id = c.id " +
                   "INNER JOIN timeentity t ON tc.time_id = t.id " +
                   "WHERE c.id = :colaboradorId", nativeQuery = true)
    PrCountEntity findByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    @Query("SELECT p FROM PrCountEntity p WHERE p.timeColaborador.id = :timeColaboradorId")
    List <PrCountEntity> findByTimeColaboradorId(@Param("timeColaboradorId") Long timeColaboradorId);

   /* PrCountEntity findByTimeColaborador_Colaborador(ColaboradorEntity colaborador);

    @Query("SELECT p FROM PrCountEntity p WHERE p.timeColaborador.colaborador.id = :colaboradorId")
    PrCountEntity findByColaboradorId(@Param("colaboradorId") Long colaboradorId);*/
    
}