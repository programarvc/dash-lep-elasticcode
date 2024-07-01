package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.PromptsHistoryDto;
import com.br.agilize.dash.model.entity.dashboardEntity.PromptsHistoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PromptsHistoryMapper extends MapperBase<PromptsHistoryEntity, PromptsHistoryDto>{
    @Override
    PromptsHistoryDto modelToDTO(PromptsHistoryEntity entity);

    @Override
    PromptsHistoryEntity dtoToModel(PromptsHistoryDto dto);
}
