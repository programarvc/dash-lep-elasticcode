package com.br.agilize.dash.model.dto;

import lombok.Data;

@Data
public class CapacidadesRecomendadasDto {
    private Long id;

   private MaturidadeDto maturidade;

   private ItemDeMaturidadeDto itemDeMaturidade;
}