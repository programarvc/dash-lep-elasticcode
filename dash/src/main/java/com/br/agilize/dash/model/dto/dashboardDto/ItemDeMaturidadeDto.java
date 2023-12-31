package com.br.agilize.dash.model.dto.dashboardDto;

import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;

import lombok.Data;

@Data
public class ItemDeMaturidadeDto {
    private Long id;

    private TiposMaturidadeEnum tipoMaturidade;
    
    private String nome;
}