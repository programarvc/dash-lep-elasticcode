package com.br.agilize.dash.model.dto.dashboardDto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MaturidadeDto {

    private Long id;

    private EsteiraDeDesenvolvimentoDto esteira;

    private LocalDate data;

    private Integer numero;

    private Double leadTime;

    private Double FrequencyDeployment;

    private Double ChangeFailureRate;

    private Double timeToRecovery;
}