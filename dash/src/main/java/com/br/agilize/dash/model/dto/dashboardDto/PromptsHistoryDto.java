package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class PromptsHistoryDto {
    private Long id;
    private String stack;
    private String tipo_codigo;
    private String entidade;
    private String tabela;
    private String prompt;
    private UserEsteiraDto userEsteira;
}
