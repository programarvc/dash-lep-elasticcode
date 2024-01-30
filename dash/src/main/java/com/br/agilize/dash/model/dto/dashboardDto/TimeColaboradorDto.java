package com.br.agilize.dash.model.dto.dashboardDto;


import com.br.agilize.dash.model.dto.ColaboradorDto;


import lombok.Data;

@Data
public class TimeColaboradorDto {

    private Long id;
    
    private TimeDto time;
    
    private ColaboradorDto colaborador;
}