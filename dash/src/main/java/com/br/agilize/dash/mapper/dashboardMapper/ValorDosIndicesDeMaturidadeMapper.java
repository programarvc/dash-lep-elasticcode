package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.ValorDosIndicesDeMaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.ValorDosIndicesDeMaturidadeEntity;

import org.mapstruct.Mapper;

@Mapper
public interface ValorDosIndicesDeMaturidadeMapper extends  MapperBase<ValorDosIndicesDeMaturidadeEntity, ValorDosIndicesDeMaturidadeDto> {
    @Override
    ValorDosIndicesDeMaturidadeDto modelToDTO(ValorDosIndicesDeMaturidadeEntity entity);

    @Override
    ValorDosIndicesDeMaturidadeEntity dtoToModel(ValorDosIndicesDeMaturidadeDto dto);
}