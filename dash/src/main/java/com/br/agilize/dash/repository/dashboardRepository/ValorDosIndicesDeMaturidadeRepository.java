package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.ValorDosIndicesDeMaturidadeEntity;
import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;

@Repository
public interface ValorDosIndicesDeMaturidadeRepository extends JpaRepository<ValorDosIndicesDeMaturidadeEntity, Long> {

    @Query("SELECT v.valorAtingido, v.valorEsperado, i.nome FROM ValorDosIndicesDeMaturidadeEntity v INNER JOIN v.itemDeMaturidade i WHERE i.id = :itemDeMaturidadeId")
    List<Object[]> findValoresAndNomeByItemDeMaturidadeId(@Param("itemDeMaturidadeId") Long itemDeMaturidadeId);

   @Query("SELECT new map (v.valorAtingido as valorAtingido, v.valorEsperado as valorEsperado, i.nome as nome) FROM ValorDosIndicesDeMaturidadeEntity v JOIN v.itemDeMaturidade i WHERE i.tipoMaturidade = :tipoMaturidade")
    List<Map<String, Object>> findValoresAndNomeByTipoMaturidade(@Param("tipoMaturidade") TiposMaturidadeEnum tipoMaturidade);

   /*  @Query("SELECT v FROM ValorDosIndicesDeMaturidadeEntity v JOIN v.maturidade m JOIN v.itemDeMaturidade i WHERE m.esteira.id = :esteiraId AND i.tipoMaturidade = :tipoMaturidade")
    List<ValorDosIndicesDeMaturidadeEntity> findByEsteiraIdAndTipoMaturidade(@Param("esteiraId") Long esteiraId, @Param("tipoMaturidade") TiposMaturidadeEnum tipoMaturidade);*/

    @Query("SELECT new map(i.nome as nome, i.tipoMaturidade as tipoMaturidade, v.valorAtingido as valorAtingido, v.valorEsperado as valorEsperado) FROM ValorDosIndicesDeMaturidadeEntity v JOIN v.maturidade m JOIN v.itemDeMaturidade i WHERE m.esteira.id = :esteiraId AND i.tipoMaturidade = :tipoMaturidade")
    List<Map<String, Object>> findByEsteiraIdAndTipoMaturidade(@Param("esteiraId") Long esteiraId, @Param("tipoMaturidade") TiposMaturidadeEnum tipoMaturidade);
} 