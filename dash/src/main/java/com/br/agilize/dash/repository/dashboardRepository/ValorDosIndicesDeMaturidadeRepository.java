package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import java.time.LocalDateTime;

import java.util.stream.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.ValorDosIndicesDeMaturidadeEntity;
import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;

@Repository
public interface ValorDosIndicesDeMaturidadeRepository extends JpaRepository<ValorDosIndicesDeMaturidadeEntity, Long> {

    @Query("SELECT new map(i.nome as nome, i.tipoMaturidade as tipoMaturidade, v.valorAtingido as valorAtingido, v.valorEsperado as valorEsperado) FROM ValorDosIndicesDeMaturidadeEntity v JOIN v.maturidade m JOIN v.itemDeMaturidade i WHERE m.esteira.id = :esteiraId AND i.tipoMaturidade = :tipoMaturidade")
    List<Map<String, Object>> findByEsteiraIdAndTipoMaturidade(@Param("esteiraId") Long esteiraId, @Param("tipoMaturidade") TiposMaturidadeEnum tipoMaturidade);

    /*@Query("SELECT v.itemDeMaturidade.nome FROM ValorDosIndicesDeMaturidadeEntity v WHERE v.maturidade.esteira.id = :esteiraId ORDER BY v.maturidade.dataHora DESC")
    Stream<String> findLatestItemDeMaturidadeByEsteiraId(@Param("esteiraId") Long esteiraId);*/

    @Query("SELECT MAX(v.maturidade.dataHora) FROM ValorDosIndicesDeMaturidadeEntity v WHERE v.maturidade.esteira.id = :esteiraId")
    Optional<LocalDateTime> findLatestDataHoraByEsteiraId(@Param("esteiraId") Long esteiraId);

    @Query("SELECT v.itemDeMaturidade.nome FROM ValorDosIndicesDeMaturidadeEntity v WHERE v.maturidade.esteira.id = :esteiraId AND v.maturidade.dataHora = :dataHora")
    List<String> findItemDeMaturidadeByEsteiraIdAndDataHora(@Param("esteiraId") Long esteiraId, @Param("dataHora") LocalDateTime dataHora);
   
}