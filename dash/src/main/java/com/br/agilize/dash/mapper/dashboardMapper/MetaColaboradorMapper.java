package com.br.agilize.dash.mapper.dashboardMapper;
import org.mapstruct.Mapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.MetaColaboradorDto;
import com.br.agilize.dash.model.entity.dashboardEntity.MetaColaboradorEntity;

@Mapper

public interface MetaColaboradorMapper extends  MapperBase<MetaColaboradorEntity , MetaColaboradorDto> {
    
    @Override
    MetaColaboradorDto modelToDTO(MetaColaboradorEntity entity);

    @Override
    MetaColaboradorEntity dtoToModel(MetaColaboradorDto dto);

}