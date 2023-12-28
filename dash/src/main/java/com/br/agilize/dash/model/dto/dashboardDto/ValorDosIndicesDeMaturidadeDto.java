package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class ValorDosIndicesDeMaturidadeDto {
    private Long id;

   private MaturidadeDto maturidade;

   private ItemDeMaturidadeDto itemDeMaturidade;

    private Double valorAtingido;

    private Double valorEsperado;

}