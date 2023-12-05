package com.br.agilize.dash.model.dto;

import lombok.Data;

@Data
public class CompetenciaColaboradorDto {
    private Long id;
    private CompetenciaDto competencia;
    private ColaboradorDto colaborador;
    private Integer progresso;
}
