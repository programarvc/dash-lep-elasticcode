package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.MaturidadeDto;
import com.br.agilize.dash.model.entity.MaturidadeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MaturidadeMapper extends  MapperBase<MaturidadeEntity, MaturidadeDto> {
    @Override
    MaturidadeDto modelToDTO(MaturidadeEntity entity);

    @Override
    MaturidadeEntity dtoToModel(MaturidadeDto dto);
}