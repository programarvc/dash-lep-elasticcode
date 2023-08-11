package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.CompetenciaColaboradorDto;
import com.br.agilize.dash.model.entity.CompetenciaColaboradorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CompetenciaColaboradorMapper extends  MapperBase<CompetenciaColaboradorEntity, CompetenciaColaboradorDto> {
    @Override
    CompetenciaColaboradorDto modelToDTO(CompetenciaColaboradorEntity entity);

    @Override
    CompetenciaColaboradorEntity dtoToModel(CompetenciaColaboradorDto dto);
}
