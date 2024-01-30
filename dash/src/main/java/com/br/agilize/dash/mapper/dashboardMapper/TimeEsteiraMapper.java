package com.br.agilize.dash.mapper.dashboardMapper;
import org.mapstruct.Mapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.TimeEsteiraDto;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeEsteiraEntity;

@Mapper

public interface TimeEsteiraMapper extends  MapperBase<TimeEsteiraEntity , TimeEsteiraDto> {
    @Override

    TimeEsteiraDto modelToDTO(TimeEsteiraEntity entity);

    @Override

    TimeEsteiraEntity dtoToModel(TimeEsteiraDto dto);

}