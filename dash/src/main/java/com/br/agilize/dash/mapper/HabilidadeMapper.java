package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.HabilidadeDto;
import com.br.agilize.dash.model.entity.HabilidadeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface HabilidadeMapper extends  MapperBase<HabilidadeEntity, HabilidadeDto> {
    @Override
    HabilidadeDto modelToDTO(HabilidadeEntity entity);

    @Override
    HabilidadeEntity dtoToModel(HabilidadeDto dto);
}