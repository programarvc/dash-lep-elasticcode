package com.br.agilize.dash.mapper;

import com.br.agilize.dash.model.dto.ItemDeMaturidadeDto;
import com.br.agilize.dash.model.entity.ItemDeMaturidadeEntity;

import org.mapstruct.Mapper;

@Mapper
public interface ItemDeMaturidadeMapper extends  MapperBase<ItemDeMaturidadeEntity, ItemDeMaturidadeDto> {
    @Override
    ItemDeMaturidadeDto modelToDTO(ItemDeMaturidadeEntity entity);

    @Override
    ItemDeMaturidadeEntity dtoToModel(ItemDeMaturidadeDto dto);
}