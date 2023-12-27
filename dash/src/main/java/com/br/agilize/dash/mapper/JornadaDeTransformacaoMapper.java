package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.JornadaDeTransformacaoDto;
import com.br.agilize.dash.model.entity.JornadaDeTransformacaoEntity;

import org.mapstruct.Mapper;

@Mapper
public interface JornadaDeTransformacaoMapper extends  MapperBase<JornadaDeTransformacaoEntity, JornadaDeTransformacaoDto> {
    @Override
    JornadaDeTransformacaoDto modelToDTO(JornadaDeTransformacaoEntity entity);

    @Override
    JornadaDeTransformacaoEntity dtoToModel(JornadaDeTransformacaoDto dto);
}