package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.AcoesDto;
import com.br.agilize.dash.model.entity.AcoesEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AcoesMapper extends  MapperBase<AcoesEntity, AcoesDto> {
    @Override
    AcoesDto modelToDTO(AcoesEntity entity);

    @Override
    AcoesEntity dtoToModel(AcoesDto dto);
}