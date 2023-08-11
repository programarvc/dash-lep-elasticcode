package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.CompetenciaDto;
import com.br.agilize.dash.model.entity.CompetenciaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CompetenciaMapper extends  MapperBase<CompetenciaEntity, CompetenciaDto> {
    @Override
    CompetenciaDto modelToDTO(CompetenciaEntity entity);

    @Override
    CompetenciaEntity dtoToModel(CompetenciaDto dto);
}
