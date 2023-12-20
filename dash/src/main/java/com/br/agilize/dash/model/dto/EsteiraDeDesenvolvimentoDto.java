package com.br.agilize.dash.model.dto;

import lombok.Data;

@Data
public class EsteiraDeDesenvolvimentoDto {

    private Long id;

    private String nome;

    private String tipo;

    private EmpresaDto empresa;

}