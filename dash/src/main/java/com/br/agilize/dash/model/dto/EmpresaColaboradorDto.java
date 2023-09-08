package com.br.agilize.dash.model.dto;

import lombok.Data;

@Data
public class EmpresaColaboradorDto {
    private Long id;
    private EmpresaDto empresa;
    private ColaboradorDto colaborador;
  
}
