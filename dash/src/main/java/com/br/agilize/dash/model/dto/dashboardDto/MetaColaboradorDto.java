package com.br.agilize.dash.model.dto.dashboardDto;


import java.time.LocalDate;

import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.CompetenciaDto;

import lombok.Data;

@Data
public class MetaColaboradorDto {

    private Long id;
    
    private Integer nota;

    private CompetenciaDto competencia;
    
    private ColaboradorDto colaborador;

    private LocalDate data;
}