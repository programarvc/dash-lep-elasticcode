package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.JornadaDeTransformacaoDto;
import com.br.agilize.dash.model.entity.dashboardEntity.JornadaDeTransformacaoEntity;

import org.mapstruct.Mapper;

@Mapper
public interface JornadaDeTransformacaoMapper extends  MapperBase<JornadaDeTransformacaoEntity, JornadaDeTransformacaoDto> {
    @Override
    JornadaDeTransformacaoDto modelToDTO(JornadaDeTransformacaoEntity entity);

    @Override
    JornadaDeTransformacaoEntity dtoToModel(JornadaDeTransformacaoDto dto);
}