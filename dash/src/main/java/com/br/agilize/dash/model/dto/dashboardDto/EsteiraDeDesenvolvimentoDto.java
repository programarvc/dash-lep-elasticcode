package com.br.agilize.dash.model.dto.dashboardDto;

import com.br.agilize.dash.model.dto.EmpresaDto;
import com.br.agilize.dash.model.enums.TiposEnum;
import com.br.agilize.dash.model.dto.dashboardDto.PromptsHistoryDto;

import lombok.Data;

import java.util.List;

@Data
public class EsteiraDeDesenvolvimentoDto {

    private Long id;
    private String nome;
    private TiposEnum tipo;
    private EmpresaDto empresa;
    private List<PromptsHistoryDto> promptsHistory;
}