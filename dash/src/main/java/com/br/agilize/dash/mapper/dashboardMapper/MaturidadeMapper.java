package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.MaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.MaturidadeEntity;

import org.mapstruct.Mapper;

@Mapper
public interface MaturidadeMapper extends  MapperBase<MaturidadeEntity, MaturidadeDto> {
    @Override
    MaturidadeDto modelToDTO(MaturidadeEntity entity);

    @Override
    MaturidadeEntity dtoToModel(MaturidadeDto dto);
}