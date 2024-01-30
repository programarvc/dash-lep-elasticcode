package com.br.agilize.dash.mapper.dashboardMapper;
import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.TimeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TimeMapper extends  MapperBase<TimeEntity, TimeDto> {
    @Override
    TimeDto modelToDTO(TimeEntity entity);

    @Override
    TimeEntity dtoToModel(TimeDto dto);
}