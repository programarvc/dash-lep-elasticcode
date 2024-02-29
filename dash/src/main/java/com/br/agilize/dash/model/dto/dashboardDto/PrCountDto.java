package com.br.agilize.dash.model.dto.dashboardDto;

import com.br.agilize.dash.model.dto.ColaboradorDto;

import lombok.Data;

@Data
public class PrCountDto {

    private Long id;

    private ColaboradorDto colaborador;

    private Integer count; 
}