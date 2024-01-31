package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ValorDosIndicesDeMaturidadeDto {
    private Long id;
    
    private LocalDateTime dataHoraValor;

    private MaturidadeDto maturidade;

    private ItemDeMaturidadeDto itemDeMaturidade;

    private Double valorAtingido;

    private Double valorEsperado;

}