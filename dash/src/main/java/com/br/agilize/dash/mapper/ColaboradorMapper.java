package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ColaboradorMapper extends  MapperBase<ColaboradorEntity, ColaboradorDto> {
    @Override
    ColaboradorDto modelToDTO(ColaboradorEntity entity);

    @Override
    ColaboradorEntity dtoToModel(ColaboradorDto dto);
}
