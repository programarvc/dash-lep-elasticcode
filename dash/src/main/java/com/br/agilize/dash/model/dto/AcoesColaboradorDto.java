package com.br.agilize.dash.model.dto;

import lombok.Data;

@Data
public class AcoesColaboradorDto {
    private Long id;
    private AcoesDto acao;
    private ColaboradorDto colaborador;
    private Integer progresso;
}
