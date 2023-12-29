package com.br.agilize.dash.repository.dashboardRepository;

import java.util.List;

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

   @Query("SELECT v.valorAtingido, v.valorEsperado, i.nome FROM ValorDosIndicesDeMaturidadeEntity v INNER JOIN v.itemDeMaturidade i WHERE i.tipoMaturidade = :tipoMaturidade")
    List<Object[]> findValoresAndNomeByTipoMaturidade(@Param("tipoMaturidade") TiposMaturidadeEnum tipoMaturidade);
}