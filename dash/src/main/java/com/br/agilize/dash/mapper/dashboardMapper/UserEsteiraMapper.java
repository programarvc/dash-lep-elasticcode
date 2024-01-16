package com.br.agilize.dash.mapper.dashboardMapper;
import org.mapstruct.Mapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.UserEsteiraDto;
import com.br.agilize.dash.model.entity.dashboardEntity.UserEsteiraEntity;

@Mapper
public interface UserEsteiraMapper extends  MapperBase<UserEsteiraEntity , UserEsteiraDto> {
   
    @Override
    UserEsteiraDto modelToDTO(UserEsteiraEntity entity);

    @Override
    UserEsteiraEntity dtoToModel(UserEsteiraDto dto);

}