package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class PromptEsteiraDto {
    private Long id;
    private PromptsHistoryDto prompt;
    private EsteiraDeDesenvolvimentoDto esteira;
}
