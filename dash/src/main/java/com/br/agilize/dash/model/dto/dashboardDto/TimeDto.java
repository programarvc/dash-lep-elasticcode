package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class TimeDto {
    private Long id;
    private String nomeTime;
    private EsteiraDeDesenvolvimentoDto esteira;
}
