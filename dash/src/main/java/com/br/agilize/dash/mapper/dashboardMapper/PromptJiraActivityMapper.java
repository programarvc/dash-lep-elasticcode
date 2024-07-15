package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.PromptJiraActivityDto;
import com.br.agilize.dash.model.entity.dashboardEntity.PromptJiraActivityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface PromptJiraActivityMapper extends MapperBase<PromptJiraActivityEntity, PromptJiraActivityDto> {

    @Override
    @Mapping(target = ".", source = ".", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PromptJiraActivityDto modelToDTO(PromptJiraActivityEntity entity);

    @Override
    @Mapping(target = ".", source = ".", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PromptJiraActivityEntity dtoToModel(PromptJiraActivityDto dto);
}
