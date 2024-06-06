package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.JiraActivitiesDto;
import com.br.agilize.dash.model.entity.dashboardEntity.JiraActivitiesEntity;

import org.mapstruct.Mapper;

@Mapper
public interface JiraActivitiesMapper extends  MapperBase<JiraActivitiesEntity, JiraActivitiesDto> {
    @Override
    JiraActivitiesDto modelToDTO(JiraActivitiesEntity entity);

    @Override
    JiraActivitiesEntity dtoToModel(JiraActivitiesDto dto);   
}