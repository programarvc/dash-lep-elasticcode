package com.br.agilize.dash.model.dto;

import lombok.Data;

@Data
public class HabilidadeColaboradorDto {
    private Long id;
    private HabilitadeDto habilidade;
    private ColaboradorDto colaborador;
  
}
