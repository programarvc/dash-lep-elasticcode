package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.EsteiraDeDesenvolvimentoDto;
import com.br.agilize.dash.model.entity.EsteiraDeDesenvolvimentoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EsteiraDeDesenvolvimentoMapper extends  MapperBase<EsteiraDeDesenvolvimentoEntity, EsteiraDeDesenvolvimentoDto> {
    @Override
    EsteiraDeDesenvolvimentoDto modelToDTO(EsteiraDeDesenvolvimentoEntity entity);

    @Override
    EsteiraDeDesenvolvimentoEntity dtoToModel(EsteiraDeDesenvolvimentoDto dto);
}