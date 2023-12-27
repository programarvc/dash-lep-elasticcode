package com.br.agilize.dash.model.dto;

import com.br.agilize.dash.model.enums.TiposEnum;

import lombok.Data;

@Data
public class EsteiraDeDesenvolvimentoDto {

    private Long id;

    private String nome;

    private TiposEnum tipo;

    private EmpresaDto empresa;

}