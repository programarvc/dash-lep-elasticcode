package com.br.agilize.dash.mapper.dashboardMapper;
import org.mapstruct.Mapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.MetasOneAOneDto;
import com.br.agilize.dash.model.entity.dashboardEntity.MetasOneAOneEntity;

@Mapper
public interface MetasOneAOneMapper extends  MapperBase<MetasOneAOneEntity ,MetasOneAOneDto> {
    
    @Override
    MetasOneAOneDto modelToDTO(MetasOneAOneEntity entity);

    @Override
    MetasOneAOneEntity dtoToModel(MetasOneAOneDto dto);

} 