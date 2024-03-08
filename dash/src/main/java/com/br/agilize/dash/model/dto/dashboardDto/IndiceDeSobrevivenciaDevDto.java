package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class  IndiceDeSobrevivenciaDevDto {
    private Long id;

    private TimeColaboradorDto timeColaborador;
   
    private Double valorIndice;
}