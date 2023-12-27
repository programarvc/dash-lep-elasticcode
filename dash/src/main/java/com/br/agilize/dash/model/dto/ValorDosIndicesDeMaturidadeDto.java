package com.br.agilize.dash.model.dto;

import lombok.Data;

@Data
public class ValorDosIndicesDeMaturidadeDto {
    private Long id;

   private MaturidadeDto maturidade;

   private ItemDeMaturidadeDto itemDeMaturidade;

    private Integer valorAtingido;

    private Integer valorEsperado;

}