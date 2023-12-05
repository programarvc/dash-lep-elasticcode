package com.br.agilize.dash.model.response;

import com.br.agilize.dash.model.dto.ColaboradorDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColaboradorResponse extends BaseResponse {
    private ColaboradorDto colaborador;
}
