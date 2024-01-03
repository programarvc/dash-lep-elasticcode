package com.br.agilize.dash.repository.dashboardRepository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.CapacidadesRecomendadasEntity;


@Repository
public interface CapacidadesRecomendadasRepository extends JpaRepository<CapacidadesRecomendadasEntity, Long> {

    @Query("SELECT new map(i.nome as nome) FROM CapacidadesRecomendadasEntity c JOIN c.maturidade m JOIN c.itemDeMaturidade i WHERE m.esteira.id = :esteiraId")
    List<Map<String, Object>> findByEsteiraId(@Param("esteiraId") Long esteiraId);
}