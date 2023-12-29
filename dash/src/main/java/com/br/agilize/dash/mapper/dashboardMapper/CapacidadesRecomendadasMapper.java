package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.model.entity.dashboardEntity.CapacidadesRecomendadasEntity;

import org.mapstruct.Mapper;

@Mapper
public interface CapacidadesRecomendadasMapper extends  MapperBase<CapacidadesRecomendadasEntity, CapacidadesRecomendadasDto> {
    @Override
    CapacidadesRecomendadasDto modelToDTO(CapacidadesRecomendadasEntity entity);

    @Override
    CapacidadesRecomendadasEntity dtoToModel(CapacidadesRecomendadasDto dto);
}