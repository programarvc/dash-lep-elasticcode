package com.br.agilize.dash.model.dto.dashboardDto;


import java.time.LocalDateTime;
import com.br.agilize.dash.model.dto.ColaboradorDto;


import lombok.Data;

@Data
public class MetasColaboradorDto {

    private Long id;
    
    private MetasOneAOneDto meta;
    
    private ColaboradorDto colaborador;

    private LocalDateTime data;
}