package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.TasksCountJiraDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TasksCountJiraEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TasksCountJiraMapper extends  MapperBase<TasksCountJiraEntity, TasksCountJiraDto> {
    @Override
    TasksCountJiraDto modelToDTO(TasksCountJiraEntity entity);

    @Override
    TasksCountJiraEntity dtoToModel(TasksCountJiraDto dto);   
}