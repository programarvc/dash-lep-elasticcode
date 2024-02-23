package com.br.agilize.dash.mapper.dashboardMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.MetasColaboradorDto;
import com.br.agilize.dash.model.entity.dashboardEntity.MetasColaboradorEntity;

@Mapper

public interface MetasColaboradorMapper extends  MapperBase<MetasColaboradorEntity , MetasColaboradorDto> {
    
    @Override
    MetasColaboradorDto modelToDTO(MetasColaboradorEntity entity);

    @Override
    MetasColaboradorEntity dtoToModel(MetasColaboradorDto dto);

}