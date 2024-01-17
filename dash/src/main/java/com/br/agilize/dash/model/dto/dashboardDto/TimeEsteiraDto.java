package com.br.agilize.dash.model.dto.dashboardDto;

import com.br.agilize.dash.model.dto.ColaboradorDto;

import lombok.Data;

@Data
public class TimeEsteiraDto {
    private Long id;
   
    private TimeDto time;

    private ColaboradorDto colaborador;

    private EsteiraDeDesenvolvimentoDto esteira;
    



}