package com.br.agilize.dash.model.dto.dashboardDto;

import com.br.agilize.dash.model.dto.ColaboradorDto;
import lombok.Data;

@Data
public class PromptColaboradorDto {
    private Long id;
    private PromptsHistoryDto prompt;
    private ColaboradorDto colaborador;
}
