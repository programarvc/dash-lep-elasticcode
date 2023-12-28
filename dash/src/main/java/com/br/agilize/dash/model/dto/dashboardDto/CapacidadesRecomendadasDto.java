package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class CapacidadesRecomendadasDto {
    private Long id;

   private MaturidadeDto maturidade;

   private ItemDeMaturidadeDto itemDeMaturidade;
}