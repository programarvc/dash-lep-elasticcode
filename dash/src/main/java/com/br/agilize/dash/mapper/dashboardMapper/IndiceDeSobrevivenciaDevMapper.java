package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.IndiceDeSobrevivenciaDevDto;
import com.br.agilize.dash.model.entity.dashboardEntity.IndiceDeSobrevivenciaDevEntity;

import org.mapstruct.Mapper;

@Mapper
public interface IndiceDeSobrevivenciaDevMapper extends  MapperBase<IndiceDeSobrevivenciaDevEntity, IndiceDeSobrevivenciaDevDto> {
    @Override
    IndiceDeSobrevivenciaDevDto modelToDTO(IndiceDeSobrevivenciaDevEntity entity);

    @Override
    IndiceDeSobrevivenciaDevEntity dtoToModel(IndiceDeSobrevivenciaDevDto dto);
}