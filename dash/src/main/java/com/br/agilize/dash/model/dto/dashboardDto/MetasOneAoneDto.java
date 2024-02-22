package com.br.agilize.dash.model.dto.dashboardDto;


import java.time.LocalDate;

import com.br.agilize.dash.model.dto.ColaboradorDto;


import lombok.Data;

@Data
public class MetasOneAOneDto {

    private Long id;
    private ColaboradorDto colaborador;
    private String meta;
    private LocalDate data;
}