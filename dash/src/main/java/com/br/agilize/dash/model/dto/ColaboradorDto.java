package com.br.agilize.dash.model.dto;

import com.br.agilize.dash.model.dto.dashboardDto.PromptsHistoryDto;
import lombok.Data;

import java.util.List;

@Data
public class ColaboradorDto {
    private Long id;
    private String nome;
    private String email;
    private String github;
    private String jiraId;
    private String miniBio; // Mini Biografia
    private List<HabilidadeDto> habilidades;
    private List<EmpresaDto> empresas;
}