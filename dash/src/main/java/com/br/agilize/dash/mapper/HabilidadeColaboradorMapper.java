package com.br.agilize.dash.mapper;


import com.br.agilize.dash.model.dto.HabilidadeColaboradorDto;
import com.br.agilize.dash.model.entity.HabilidadeColaboradorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface HabilidadeColaboradorMapper extends  MapperBase<HabilidadeColaboradorEntity, HabilidadeColaboradorDto> {
    @Override
    HabilidadeColaboradorDto modelToDTO(HabilidadeColaboradorEntity entity);

    @Override
    HabilidadeColaboradorEntity dtoToModel(HabilidadeColaboradorDto dto);
}
