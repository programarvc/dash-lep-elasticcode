package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.HabilitadeDto;
import com.br.agilize.dash.model.entity.HabilitadeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface HabilidadeMapper extends  MapperBase<HabilitadeEntity, HabilitadeDto> {
    @Override
    HabilitadeDto modelToDTO(HabilitadeEntity entity);

    @Override
    HabilitadeEntity dtoToModel(HabilitadeDto dto);
}
