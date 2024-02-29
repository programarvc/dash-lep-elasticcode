package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.PrCountDto;
import com.br.agilize.dash.model.entity.dashboardEntity.PrCountEntity;

import org.mapstruct.Mapper;

@Mapper
public interface PrCountMapper extends  MapperBase<PrCountEntity, PrCountDto> {
    @Override
    PrCountDto modelToDTO(PrCountEntity entity);

    @Override
    PrCountEntity dtoToModel(PrCountDto dto);
}