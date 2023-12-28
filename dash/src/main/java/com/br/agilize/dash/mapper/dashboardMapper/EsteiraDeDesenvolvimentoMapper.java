package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.EsteiraDeDesenvolvimentoDto;
import com.br.agilize.dash.model.entity.dashboardEntity.EsteiraDeDesenvolvimentoEntity;

import org.mapstruct.Mapper;

@Mapper
public interface EsteiraDeDesenvolvimentoMapper extends  MapperBase<EsteiraDeDesenvolvimentoEntity, EsteiraDeDesenvolvimentoDto> {
    @Override
    EsteiraDeDesenvolvimentoDto modelToDTO(EsteiraDeDesenvolvimentoEntity entity);

    @Override
    EsteiraDeDesenvolvimentoEntity dtoToModel(EsteiraDeDesenvolvimentoDto dto);
}