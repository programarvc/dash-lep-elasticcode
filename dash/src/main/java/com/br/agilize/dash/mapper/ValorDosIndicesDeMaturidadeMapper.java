package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.ValorDosIndicesDeMaturidadeDto;
import com.br.agilize.dash.model.entity.ValorDosIndicesDeMaturidadeEntity;

import org.mapstruct.Mapper;

@Mapper
public interface ValorDosIndicesDeMaturidadeMapper extends  MapperBase<ValorDosIndicesDeMaturidadeEntity, ValorDosIndicesDeMaturidadeDto> {
    @Override
    ValorDosIndicesDeMaturidadeDto modelToDTO(ValorDosIndicesDeMaturidadeEntity capacidadesRecomendadasEntity);

    @Override
    ValorDosIndicesDeMaturidadeEntity dtoToModel(ValorDosIndicesDeMaturidadeDto dto);
}