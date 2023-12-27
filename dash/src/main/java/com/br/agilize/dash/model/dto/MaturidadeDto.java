package com.br.agilize.dash.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MaturidadeDto {

    private Long id;

    private EsteiraDeDesenvolvimentoDto esteira;

    private LocalDate data;

    private Integer numero;

    private Integer leadTime;

    private Integer FrequencyDeployment;

    private Double ChangeFailureRate;

    private Integer timeToRecovery;
}