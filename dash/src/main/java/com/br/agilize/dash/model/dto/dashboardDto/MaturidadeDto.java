package com.br.agilize.dash.model.dto.dashboardDto;

import java.time.*;

import lombok.Data;

@Data
public class MaturidadeDto {

    private Long id;

    private EsteiraDeDesenvolvimentoDto esteira;

    private LocalDateTime dataHora;

    private Integer numero;

    private Double leadTime;

    private Double leadTimeEsperado;

    private Double FrequencyDeployment;

    private Double frequencyDeploymentEsperado;

    private Double ChangeFailureRate;

    private Double changeFailureRateEsperado;

    private Double timeToRecovery;

    private Double timeToRecoveryEsperado;

    private Double mediaDeJornada;

    private Double saude;

    private Double metricas4;

    private Double capacidadeDora;
}