package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.model.entity.CapacidadesRecomendadasEntity;

import org.mapstruct.Mapper;

@Mapper
public interface CapacidadesRecomendadasMapper extends  MapperBase<CapacidadesRecomendadasEntity, CapacidadesRecomendadasDto> {
    @Override
    CapacidadesRecomendadasDto modelToDTO(CapacidadesRecomendadasEntity entity);

    @Override
    CapacidadesRecomendadasEntity dtoToModel(CapacidadesRecomendadasDto dto);
}