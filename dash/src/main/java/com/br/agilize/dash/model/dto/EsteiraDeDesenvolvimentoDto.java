package com.br.agilize.dash.model.dto;


import java.util.List;

import com.br.agilize.dash.model.enums.TiposEnum;

import lombok.Data;

@Data
public class EsteiraDeDesenvolvimentoDto {

    private Long id;

    private String nome;

    private TiposEnum tipo;

    private EmpresaDto empresa;

}