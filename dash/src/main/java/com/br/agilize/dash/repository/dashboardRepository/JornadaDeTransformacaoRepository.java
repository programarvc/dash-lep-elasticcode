
package com.br.agilize.dash.repository.dashboardRepository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.JornadaDeTransformacaoEntity;

@Repository
public interface JornadaDeTransformacaoRepository extends JpaRepository<JornadaDeTransformacaoEntity, Long> {

    
    @Query("SELECT new map(j.saude as saude, j.metricas4 as metricas4, j.capacidadeDora as capacidadeDora, j.mediaDeJornada as mediaDeJornada) FROM JornadaDeTransformacaoEntity j JOIN j.maturidade m WHERE m.esteira.id = :esteiraId")
    Map<String, Object> findByEsteiraId(@Param("esteiraId") Long esteiraId);
}

