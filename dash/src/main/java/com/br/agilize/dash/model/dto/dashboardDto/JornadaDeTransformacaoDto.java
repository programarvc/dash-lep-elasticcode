package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class JornadaDeTransformacaoDto {
    private Long id;

    private Integer saude;

    private Integer metricas4;

    private Integer capacidadeDora;

    private MaturidadeDto maturidade;

    private Double mediaDeJornada;
}