package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class PrCountDto {

    private Long id;

    private TimeColaboradorDto timeColaborador;

    private Integer count; 
}