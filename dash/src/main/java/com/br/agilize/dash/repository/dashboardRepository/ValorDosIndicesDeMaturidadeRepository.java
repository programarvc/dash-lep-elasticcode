package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import java.time.LocalDateTime;

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

    @Query("SELECT v FROM ValorDosIndicesDeMaturidadeEntity v WHERE v.maturidade.esteira.id = :esteiraId AND v.itemDeMaturidade.tipoMaturidade = :tipoMaturidade ")
    List<ValorDosIndicesDeMaturidadeEntity> findLatestByEsteiraIdAndTipoMaturidade(@Param("esteiraId") Long esteiraId, @Param("tipoMaturidade") TiposMaturidadeEnum tipoMaturidade);

   
    @Query(value = "SELECT m.id, im.tipo_maturidade, im.nome, vim.valor_atingido, vim.valor_esperado FROM valordosindicesdematuridadeentity AS vim INNER JOIN maturidadeentity AS m ON m.id = vim.maturidade_id INNER JOIN itemdematuridadeentity AS im ON im.id = vim.item_de_maturidade_id WHERE m.id = :maturidadeId", nativeQuery = true)
    List<Object[]> findLatestByEsteiraIdAndTipoMaturidade(@Param("maturidadeId") Long maturidadeId);
   
    //retorna os dados de acordo com a data de maturidade id em ordem cresente
    @Query(value = "SELECT im.tipo_maturidade, im.nome, m.data_hora, vim.id, vim.valor_atingido as valorAtingido, vim.valor_esperado, vim.item_de_maturidade_id, vim.maturidade_id as maturidade_id FROM valordosindicesdematuridadeentity vim INNER JOIN maturidadeentity m ON m.id = vim.maturidade_id INNER JOIN itemdematuridadeentity im ON im.id = vim.item_de_maturidade_id WHERE m.id = :maturidadeId ORDER BY m.data_hora DESC", nativeQuery = true)
    List<Object[]> findByMaturidadeId(@Param("maturidadeId") Long maturidadeId);

    

        List<ValorDosIndicesDeMaturidadeEntity> findValorDoIndicesByMaturidadeId(Long maturidadeId);

   
   

   
}