package com.br.agilize.dash.repository.dashboardRepository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.IndiceDeSobrevivenciaDevEntity;


@Repository
public interface IndiceDeSobrevivenciaDevRepository extends JpaRepository<IndiceDeSobrevivenciaDevEntity, Long> {
    @Query("SELECT new map(i.valorIndice as valorIndice) FROM IndiceDeSobrevivenciaDevEntity i WHERE i.timeColaborador.colaborador.id = :colaboradorId")
    Map<String, Object> findValorIndiceByColaboradorId(@Param("colaboradorId") Long colaboradorId);

}