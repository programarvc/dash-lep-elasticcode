package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.ItemDeMaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.ItemDeMaturidadeEntity;

import org.mapstruct.Mapper;

@Mapper
public interface ItemDeMaturidadeMapper extends  MapperBase<ItemDeMaturidadeEntity, ItemDeMaturidadeDto> {
    @Override
    ItemDeMaturidadeDto modelToDTO(ItemDeMaturidadeEntity entity);

    @Override
    ItemDeMaturidadeEntity dtoToModel(ItemDeMaturidadeDto dto);
}