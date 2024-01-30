package com.br.agilize.dash.mapper.dashboardMapper;
import org.mapstruct.Mapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.TimeColaboradorDto;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeColaboradorEntity;

@Mapper

public interface TimeColaboradorMapper extends  MapperBase<TimeColaboradorEntity , TimeColaboradorDto> {
    
    @Override
    TimeColaboradorDto modelToDTO(TimeColaboradorEntity entity);

    @Override
    TimeColaboradorEntity dtoToModel(TimeColaboradorDto dto);

}